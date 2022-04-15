package com.ivan.netty.client;

import com.ivan.netty.common.message.Message;
import com.ivan.netty.common.message.OtherMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class NettyClient implements Runnable {
    /**
     * 最大尝试次数：12
     */
    private static final int MAX_RECONNECT_TIMES = 12;
    /**
     * 每次尝试间隔：5s
     */
    private static final int RECONNECT_INTERVAL = 5;

    private final String host;
    private final Integer port;
    private Channel channel;
    private NioEventLoopGroup worker;
    private int reconnectTimes; //重连次数

    public NettyClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    private void connect() throws Exception {
        worker = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(worker)
                .channel(NioSocketChannel.class)
                .handler(new KeepAliveClientInitializer(this))
                .connect(host, port);

        channel = channelFuture.sync().channel();
        reconnectTimes = 0;
        log.info("Netty Client 连接成功：{}:{}", host, port);
        channel.closeFuture().sync();
    }

    /**
     * 连接/重连
     */
    protected void reconnect() {
        try {
            connect();
        } catch (Exception e) {
            if (worker != null) {
                worker.shutdownGracefully();
            }
            log.error("Netty Client 连接失败：{}", e.getMessage());
            // 间隔 5s 重连一次
            try {
                TimeUnit.SECONDS.sleep(RECONNECT_INTERVAL);
            } catch (InterruptedException ex) {
            }
            reconnectTimes++;
            if (reconnectTimes > MAX_RECONNECT_TIMES) {
                return;
            }
            log.info("Netty Client 正在重试连接：当前第【{}】次重试", reconnectTimes);
            reconnect();
        }
    }

    @Override
    public void run() {
        reconnect();
    }

    public void sendMsg(String msg) {
        log.info("Netty Client 开始发送消息：{}", msg);
        this.sendMsg(new OtherMessage(msg));
    }

    public void sendMsg(Message msg) {
        try {
            channel.writeAndFlush(msg);
        } catch (Exception e) {
            log.error("Netty Client 发送消息失败：{}", msg, e);
        }
    }
}
