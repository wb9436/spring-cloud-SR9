package com.ivan.service;

import com.ivan.config.FeignDefaultConfiguration;
import com.ivan.entity.RequestEntity;
import com.ivan.service.fallback.ProviderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: WB
 * @version: v1.0
 */
@FeignClient(
//        name值对应Nacos服务名
        name = "feign-example-provider",
//        configuration = FeignDefaultConfiguration.class, // 通过配置类配置，针对某个微服务进行自定义配置，局部配置

//        需要开启对 hystrix 支持【fallback，fallbackFactory容错处理，同时配置fallback有效】才有效
//        fallback = ProviderFallback.class, // 只做容错处理
        fallbackFactory = ProviderFallbackFactory.class // 不仅只做容错处理，还可获取异常详情
)
public interface FeignProviderService {

    /**
     * 测试 Feign GET 请求：使用 @RequestParam 来绑定请求参数
     */
    @GetMapping("/get")
    public String get(@RequestParam("name") String name);

    /**
     * 测试 Feign POST 请求：使用 @RequestParam 来绑定请求参数
     */
    @PostMapping("/post")
    public String post(@RequestParam("name") String name);

    /**
     * 测试 Feign 请求（参数路径中）：使用 @PathVariable 来绑定请求参数
     */
    @RequestMapping("/path/{name}")
    public String path(@PathVariable("name") String name);

    /**
     * 测试 Feign POST 请求：使用 @RequestParam 来绑定请求参数
     */
    @PostMapping("/body")
    public String body(@RequestBody RequestEntity requestEntity);
}
