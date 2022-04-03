package com.ivan.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 自定义Feign请求拦截器
 *
 * @author: WB
 * @version: v1.0
 */
public class MyFeignRequestInterceptor implements RequestInterceptor {

    private static final String TOKEN_KEY = "token";
    private static final String TOKEN_VALUE = "1016065991";

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template, TOKEN_KEY, TOKEN_VALUE);
    }

    private void addHeader(RequestTemplate requestTemplate, String key, String... values) {
        if (!requestTemplate.headers().containsKey(key)) {
            requestTemplate.header(key, values);
        }
    }
}
