package com.ivan.netty.redis;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.redis.RedisArrayAggregator;
import io.netty.handler.codec.redis.RedisBulkStringAggregator;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;

/**
 * @author: WB
 * @version: v1.0
 */
public class RedisClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new RedisDecoder());
        ch.pipeline().addLast(new RedisBulkStringAggregator());
        ch.pipeline().addLast(new RedisArrayAggregator());
        ch.pipeline().addLast(new RedisEncoder());
        ch.pipeline().addLast(new RedisClientHandler());
    }

}
