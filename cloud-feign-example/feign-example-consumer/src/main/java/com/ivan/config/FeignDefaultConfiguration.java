package com.ivan.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 通过配置类自定义Feign配置
 */
public class FeignDefaultConfiguration {
    // 设计Feign的日志级别
    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.BASIC;
    }
}
