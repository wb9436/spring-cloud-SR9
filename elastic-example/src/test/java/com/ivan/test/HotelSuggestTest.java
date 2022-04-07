package com.ivan.test;

import org.apache.http.HttpHost;
import org.apache.lucene.search.suggest.document.CompletionTerms;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * 测试自动补全相关功能
 *
 * @author: WB
 * @version: v1.0
 */
public class HotelSuggestTest {
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
    void testSuggestion() throws IOException {
        // 1.准备Request
        SearchRequest request = new SearchRequest("hotel");
        // 2.组织DSL语句
        request.source().suggest(new SuggestBuilder()
                .addSuggestion("key_suggestion",
                        SuggestBuilders.completionSuggestion("suggestion") // 建议补全的字段名
                                .prefix("h") // 前缀（也就是查询的key）
                                .skipDuplicates(true) // 是否跳过重复的
                                .size(10))); // 返回结果数量
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析响应
        Suggest suggest = response.getSuggest();
        CompletionSuggestion completion = suggest.getSuggestion("key_suggestion"); // 对应查询的 suggestionName
        List<CompletionSuggestion.Entry.Option> options = completion.getOptions();

        for (CompletionSuggestion.Entry.Option option : options) {
            String text = option.getText().toString();
            System.out.println(text);
        }
    }
}
