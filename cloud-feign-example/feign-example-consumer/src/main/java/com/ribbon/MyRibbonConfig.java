package com.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Robbin配置类（默认轮询）
 * <p>
 * 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说 我们达不到特殊化定制的目的了。
 *
 * @author: WB
 * @version: v1.0
 */
@Configuration
@IgnoreScan
public class MyRibbonConfig {

    @Bean
    public IRule myRule() {
//        // Ribbon 默认是轮询，我自定义为随机
//        return new RandomRule();

        // 自定义为：轮询各调用5次
        return new MyRibbonRule();
    }
}
