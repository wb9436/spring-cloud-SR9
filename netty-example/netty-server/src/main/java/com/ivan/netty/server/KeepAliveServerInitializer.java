package com.ivan.netty.server;

import com.ivan.netty.common.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: WB
 * @version: v1.0
 */
public class KeepAliveServerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 读等待 120s
     */
    private static final Integer READER_IDLE_TIME = 120;
    /**
     * 写等待 10s
     */
    private static final Integer WRITER_IDLE_TIME = 10;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        /*
//         * 使用 ObjectDecoder和 ObjectEncoder
//         * 因为双向都有写数据和读数据，所以这里需要两个都设置
//         * 如果只读，那么只需要ObjectDecoder即可
//         */
//        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingResolver(this.getClass().getClassLoader())));
//        ch.pipeline().addLast(new ObjectEncoder());

        /**
         * 使用自定义编解码器，解码器从0开始截取字节，并且包含消息头
         */
        ch.pipeline().addLast(new MessageCodec());

        /*
         * 这里只监听读操作
         * 可以根据需求，监听写操作和总得操作
         */
        ch.pipeline().addLast(new IdleStateHandler(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS));
        /*
         * 执行具体的业务逻辑
         */
        ch.pipeline().addLast(new NettyServerHandler());
    }
}
