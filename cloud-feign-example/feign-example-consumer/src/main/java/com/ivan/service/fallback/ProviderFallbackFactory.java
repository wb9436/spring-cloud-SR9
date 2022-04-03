package com.ivan.service.fallback;

import com.ivan.entity.RequestEntity;
import com.ivan.service.FeignProviderService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Feign容错处理工厂类，需要实现FallbackFactory接口，并且注册到Spring容器中
 * <p>
 * Feign容错处理工厂类可显示错误详情
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@Component
public class ProviderFallbackFactory implements FallbackFactory<FeignProviderService> {

    @Override
    public FeignProviderService create(Throwable cause) {
        return new FeignProviderService() {
            @Override
            public String get(String name) {
                log.error("Provider GET 请求失败", cause);
                return "fallbackFactory：get";
            }

            @Override
            public String post(String name) {
                log.error("Provider POST 请求失败", cause);
                return "fallbackFactory：post";
            }

            @Override
            public String path(String name) {
                log.error("Provider GET PATH 请求失败", cause);
                return "fallbackFactory：path";
            }

            @Override
            public String body(RequestEntity requestEntity) {
                log.error("Provider BODY 请求失败", cause);
                return "fallbackFactory：body";
            }
        };
    }
}
