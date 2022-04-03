package com.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Robbin全局配置类
 *
 * @author: WB
 * @version: v1.0
 */
@Configuration
@IgnoreScan
public class GlobalRibbonConfig {

    @Bean
    public IRule myRule() {
//        // Ribbon 默认是轮询，我自定义为随机
//        return new RandomRule();

        // 轮询策略
        return new RoundRobinRule();
    }
}
