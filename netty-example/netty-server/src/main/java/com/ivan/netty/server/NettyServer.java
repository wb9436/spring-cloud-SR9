package com.ivan.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class NettyServer implements Runnable {
    private final String host;
    private final Integer port;
    private NioEventLoopGroup boss;
    private NioEventLoopGroup worker;

    public NettyServer(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    private void connect() throws InterruptedException {
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();

        ChannelFuture channelFuture = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new KeepAliveServerInitializer())
                .bind(host, port);

        Channel channel = channelFuture.sync().channel();
        channel.closeFuture().sync();
    }

    @Override
    public void run() {
        try {
            log.info("Netty Server 正在启动：{}:{}", host, port);
            connect();
        } catch (InterruptedException e) {
            if (boss != null) {
                boss.shutdownGracefully();
            }
            if (worker != null) {
                worker.shutdownGracefully();
            }
        }
    }
}
