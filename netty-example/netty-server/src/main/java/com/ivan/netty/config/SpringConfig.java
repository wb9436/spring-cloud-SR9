package com.ivan.netty.config;

import com.ivan.netty.server.NettyProperties;
import com.ivan.netty.server.NettyServer;
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
    public NettyServer nettyServer(NettyProperties properties, ExecutorService singleThreadPool) {
        NettyServer nettyServer = new NettyServer(properties.getHost(), properties.getPort());
        singleThreadPool.execute(nettyServer);
        return nettyServer;
    }
}
