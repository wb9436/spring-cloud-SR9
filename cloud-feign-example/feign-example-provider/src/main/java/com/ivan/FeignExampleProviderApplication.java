package com.ivan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: WB
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignExampleProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignExampleProviderApplication.class, args);
    }

}
