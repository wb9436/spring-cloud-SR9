package com.ivan;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: WB
 * @version: v1.0
 */
@SpringBootApplication
@MapperScan("com.ivan.dao")
public class ElasticExampleApplication {

    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.152.50:9200"))
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticExampleApplication.class);
    }

}
