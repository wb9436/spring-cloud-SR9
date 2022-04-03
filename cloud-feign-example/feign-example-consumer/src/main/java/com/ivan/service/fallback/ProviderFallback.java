package com.ivan.service.fallback;

import com.ivan.entity.RequestEntity;
import com.ivan.service.FeignProviderService;
import org.springframework.stereotype.Component;

/**
 * Feign容错处理类，需要实现FeignClien注解的接口类，并且注册到Spring容器中
 *
 * @author: WB
 * @version: v1.0
 */
@Component
public class ProviderFallback implements FeignProviderService {

    @Override
    public String get(String name) {
        return "fallback：get";
    }

    @Override
    public String post(String name) {
        return "fallback：post";
    }

    @Override
    public String path(String name) {
        return "fallback：path";
    }

    @Override
    public String body(RequestEntity requestEntity) {
        return "fallback：body";
    }
}
