package com.ivan.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * 测试聚合查询相关操作
 *
 * @author: WB
 * @version: v1.0
 */
public class HotelAggregationTest {
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
    void testBucketAggregation() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.termQuery("city", "上海"));
        request.source().size(0);
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(100)
                .order(BucketOrder.count(false))
        );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        Terms brandAgg = aggregations.get("brandAgg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            System.out.println(bucket.getKey() + " ：" + bucket.getDocCount() + "家");
        }
    }

    @Test
    void testMetricAggregation() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.termQuery("city", "上海"));
        request.source().size(0);
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .subAggregation(AggregationBuilders
                        .avg("scoreAgg")
                        .field("score")
                )
                .order(BucketOrder.aggregation("scoreAgg.value", false))
        );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        Terms brandAgg = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = brandAgg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            Aggregations subAggregations = bucket.getAggregations();
            Avg scoreAgg = subAggregations.get("scoreAgg");

            System.out.println(bucket.getKey() + " ：" + bucket.getDocCount() + "家，平均评分为：" + scoreAgg.getValue());
        }
    }
}

