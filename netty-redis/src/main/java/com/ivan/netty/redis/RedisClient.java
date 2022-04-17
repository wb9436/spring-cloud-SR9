package com.ivan.netty.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author: WB
 * @version: v1.0
 */
public class RedisClient {
    private final String host;
    private final Integer port;
    private Channel channel;
    private NioEventLoopGroup worker;

    public RedisClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new RedisClientInitializer())
                    .connect(host, port);
            channel = channelFuture.sync().channel();
            channel.closeFuture().addListener(future -> {
                worker.shutdownGracefully();
            });
        } catch (Exception e) {
            worker.shutdownGracefully();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void send(String command) {
        try {
            ChannelFuture channelFuture = channel.writeAndFlush(command);
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (!future.isSuccess()) {
                        System.err.print("write failed: ");
                        future.cause().printStackTrace(System.err);
                    }
                }
            });
            /*阻塞等待返回结果*/
            channelFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        if (worker != null) {
            worker.shutdownGracefully();
        }
    }


}
