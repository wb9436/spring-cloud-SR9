package com.ivan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.dao.HotelDao;
import com.ivan.entity.Hotel;
import com.ivan.entity.HotelDoc;
import com.ivan.entity.PageResult;
import com.ivan.entity.RequestParams;
import com.ivan.service.HotelService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelDao, Hotel> implements HotelService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageResult search(RequestParams params) {
        try {
            // 1.创建请求
            SearchRequest request = new SearchRequest("hotel");
            // 2.组织 DSL 语句
            builderBasicQuery(params, request);
            // 3.发送请求
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.解析数据
            return handlerResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<String>> getFilters(RequestParams params) {
        try {
            // 1. 准备请求
            SearchRequest request = new SearchRequest("hotel");
            // 2. 准备 DSl
            // 2.1 组织查询条件
            builderBasicQuery(params, request);
            // 2.2 设置返回文档数量为0
            request.source().size(0);
            // 2.3 组织Aggregation
            builderAggregation(request);
            // 3. 发送请求
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4. 解析响应
            Map<String, List<String>> result = new HashMap<>();
            Aggregations aggregations = response.getAggregations();
            // 4.1 解析城市
            List<String> cityList = resolverAggregation(aggregations, "cityAgg");
            result.put("city", cityList);
            // 4.2 解析星级
            List<String> starList = resolverAggregation(aggregations, "starAgg");
            result.put("starName", starList);
            // 4.3 解析品牌
            List<String> brandList = resolverAggregation(aggregations, "brandAgg");
            result.put("brand", brandList);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> resolverAggregation(Aggregations aggregations, String aggName) {
        Terms cityAgg = aggregations.get(aggName);
        List<String> list = new ArrayList<>();
        for (Terms.Bucket bucket : cityAgg.getBuckets()) {
            list.add(bucket.getKey().toString());
        }
        return list;
    }

    private void builderAggregation(SearchRequest request) {
        request.source().aggregation(AggregationBuilders.terms("cityAgg").field("city").size(100));
        request.source().aggregation(AggregationBuilders.terms("brandAgg").field("brand").size(100));
        request.source().aggregation(AggregationBuilders.terms("starAgg").field("starName").size(100));
    }

    private void builderBasicQuery(RequestParams params, SearchRequest request) {
        // 1.原始查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        String key = params.getKey();
        if (!StringUtils.isEmpty(key)) {
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        } else {
            boolQuery.must(QueryBuilders.matchAllQuery());
        }
        String city = params.getCity();
        if (!StringUtils.isEmpty(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city));
        }
        String brand = params.getBrand();
        if (!StringUtils.isEmpty(brand)) {
            boolQuery.filter(QueryBuilders.termQuery("brand", brand));
        }
        String starName = params.getStarName();
        if (!StringUtils.isEmpty(starName)) {
            boolQuery.filter(QueryBuilders.termQuery("starName", starName));
        }
        if (params.getMinPrice() != null && params.getMaxPrice() != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("price")
                    .gte(params.getMinPrice()).lte(params.getMaxPrice()));
        }
        // 2.广告算分控制
        FunctionScoreQueryBuilder functionScoreQuery =
                QueryBuilders.functionScoreQuery(
                        // 2.1 原始查询
                        boolQuery,
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                        // 2.2 过滤条件
                                        QueryBuilders.termQuery("isAD", true),
                                        //2.3 算分函数
                                        ScoreFunctionBuilders.weightFactorFunction(10)
                                )
                        });
        // 2.4 算分模式
        functionScoreQuery.boostMode(CombineFunction.SUM);

        request.source().query(functionScoreQuery);
        // 3 设置排序
        String location = params.getLocation();
        // 按照距离我的距离进行排序
        // 查找附近的酒店，并按照距离由近到远排序
        if (!StringUtils.isEmpty(location)) {
            request.source().sort(
                    SortBuilders.geoDistanceSort("location", new GeoPoint(location))
                            .unit(DistanceUnit.KILOMETERS)
                            .order(SortOrder.ASC)
            );
        }
//        String sortBy = params.getSortBy();
//        if (!StringUtils.isEmpty(sortBy)) {
//            // 按价格排序
//            if ("price".equals(sortBy)) {
//                request.source().sort(
//                        SortBuilders.fieldSort("price").order(SortOrder.ASC)
//                );
//            }
//        }
        // 4 设置分页
        int page = params.getPage();
        int size = params.getSize();
        request.source().from((page - 1) * size).size(size);
        // 5.设置高亮
        if (!StringUtils.isEmpty(key)) {
            request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        }
    }

    private PageResult handlerResponse(SearchResponse response) {
        SearchHits hits = response.getHits();
        // 总查询结果条数数
        long total = hits.getTotalHits().value;
        List<HotelDoc> hotels = new ArrayList<>();
        if (total > 0) {
            for (SearchHit hit : hits.getHits()) {
                // 解析 JSON 字符串
                String json = hit.getSourceAsString();
                HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
                // 处理按照距离远近排序的返回
                Object[] sortValues = hit.getSortValues();
                if (sortValues != null && sortValues.length > 0) {
                    hotelDoc.setDistance(sortValues[0]);
                }
                // 获取高亮结果
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if (!CollectionUtils.isEmpty(highlightFields)) {
                    // 根据字段名获取高亮结果
                    HighlightField highlightField = highlightFields.get("name");
                    if (highlightField != null) {
                        // 获取高亮值
                        String name = highlightField.getFragments()[0].string();
                        // 覆盖非高亮结果
                        hotelDoc.setName(name);
                    }
                }
                hotels.add(hotelDoc);
            }
        }
        return new PageResult(total, hotels);
    }
}
