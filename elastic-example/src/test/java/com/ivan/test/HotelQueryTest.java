package com.ivan.test;

import com.alibaba.fastjson.JSON;
import com.ivan.entity.HotelDoc;
import org.apache.http.HttpHost;
import org.assertj.core.util.Arrays;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.ScoreFunction;
import org.elasticsearch.common.lucene.search.function.WeightFactorFunction;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.query.functionscore.WeightBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
public class HotelQueryTest {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.152.50:9200"))
        );
    }

    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }

    @Test
    void testMatchQuery() throws IOException {
        // 1.创建请求  GET /hotel/_search
        SearchRequest request = new SearchRequest("hotel");
        // 2.组织DSL查询条件
        request.source().query(QueryBuilders.matchQuery("city", "上海"));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析查询结果
        handlerResponse(response);
    }

    private void handlerResponse(SearchResponse response) {
        // 4.解析数据
        SearchHits hits = response.getHits();
        // 4.1结果总条数
        long total = hits.getTotalHits().value;
        System.out.println("共搜索到" + total + "条数据");
        // 4.2遍历每条数据
        for (SearchHit hit : hits.getHits()) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 获取高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    // 获取高亮值
                    String name = highlightField.getFragments()[0].string();
                    // 覆盖非高亮结果
                    hotelDoc.setName(name);
                }
            }
            System.out.println(hotelDoc);
        }
    }

    @Test
    void testMatchAllQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析查询结果
        handlerResponse(response);
    }

    @Test
    void testMultiMatchQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.multiMatchQuery("上海", "name", "city"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testTermQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.termQuery("city", "上海"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testRangeQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.rangeQuery("price").lte(200));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    // TODO geo_distance: 地理坐标使用整数无查询结果
    @Test
    void testGeoDistanceQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
//        // 地理坐标使用整数无查询结果
//        request.source().query(QueryBuilders.geoDistanceQuery("location").point(31, 121).distance("2", DistanceUnit.KILOMETERS));
        request.source().query(QueryBuilders.geoDistanceQuery("location").point(31.21, 121.5).distance("2", DistanceUnit.KILOMETERS));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testGeoBoundingBoxQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.geoBoundingBoxQuery("location")
                .setCorners(31.1, 121.5, 30.9, 121.7));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testBoolQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("city", "上海")) // 必须匹配
                .should(QueryBuilders.rangeQuery("price").lte(200)) // 应该匹配（可不匹配）
                .mustNot(QueryBuilders.matchQuery("brand", "如家")) // 必须不匹配
                .filter( // 过滤，必须匹配，
                        QueryBuilders.geoDistanceQuery("location").point(31.21, 121.5).distance("2km")
                );
        request.source().query(boolQuery);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testFunctionScoreQuery() throws IOException {
        SearchRequest request = new SearchRequest("hotel");

        // 1.原始查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchAllQuery());

        // 2.算分函数
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(
                boolQuery, //原始查询
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                QueryBuilders.termQuery("brand", "如家"), // 过滤条件
                                ScoreFunctionBuilders.weightFactorFunction(2) // 算分函数
                        )
                }
        );
        functionScoreQuery.boostMode(CombineFunction.SUM); // 运算模式

        request.source().query(functionScoreQuery);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }

    @Test
    void testHighlight() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all", "上海"));
        // 设置高亮
        request.source().highlighter(new HighlightBuilder()
                .field("name") // 对name这个字段高亮
                .requireFieldMatch(false)); // 高亮字段是否需要与搜索匹配字段一致，为true时必须一致，否则无法高亮

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        handlerResponse(response);
    }
}
