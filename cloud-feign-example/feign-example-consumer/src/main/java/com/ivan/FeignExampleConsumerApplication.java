package com.ivan;

import com.ribbon.IgnoreScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author: WB
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(excludeFilters = {
        // 被 @IgnoreScan 注解所标注的类不会被注册到Spring容器
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = IgnoreScan.class)
})
public class FeignExampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignExampleConsumerApplication.class, args);
    }

}
