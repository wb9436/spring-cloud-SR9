package com.ivan.netty.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@ConfigurationProperties(prefix = "netty.server")
public class NettyProperties {

    private String host;
    private Integer port;

}
