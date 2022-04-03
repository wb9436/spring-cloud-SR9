package com.ivan.config;

import com.ribbon.GlobalRibbonConfig;
import com.ribbon.MyRibbonConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: WB
 * @version: v1.0
 */
@Configuration
public class SpringConfig {

    /**
     * Ribbon 默认是轮询算法
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 使用 @RibbonClient，为特定的目标服务 feign-example-provider 自定义配置。
     * 使用 @RibbonClient的configuration属性，指定Ribbon的配置类。
     */
    @RibbonClient(
            // name 对应微服务服务名称
            name = "feign-example-provider",
            configuration = MyRibbonConfig.class
    )
    private static class FeignProviderRibbonClient {
        public FeignProviderRibbonClient() {
        }
    }

    /**
     * 全局配置 Ribbon 负载策略
     */
    @RibbonClients(defaultConfiguration = GlobalRibbonConfig.class)
    private static class GlobalRibbonClient {
        public GlobalRibbonClient() {
        }
    }

}
