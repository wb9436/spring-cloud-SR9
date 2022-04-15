package com.ivan.netty.config;

import com.ivan.netty.client.NettyClient;
import com.ivan.netty.client.NettyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: WB
 * @version: v1.0
 */
@Configuration
@EnableConfigurationProperties(NettyProperties.class)
public class SpringConfig {

    @Bean
    public ExecutorService singleThreadPool() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean
    public NettyClient nettyClient(NettyProperties properties, ExecutorService singleThreadPool) {
        NettyClient nettyClient = new NettyClient(properties.getHost(), properties.getPort());
        singleThreadPool.execute(nettyClient);
        return nettyClient;
    }

}
