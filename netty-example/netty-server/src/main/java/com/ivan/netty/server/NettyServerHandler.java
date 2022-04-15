package com.ivan.netty.server;

import com.ivan.netty.common.message.OtherMessage;
import com.ivan.netty.common.message.Ping;
import com.ivan.netty.common.message.Pong;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /*连接成功后触发，只触发一次*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("客户端连接成功：{}:{}", socketAddress.getHostString(), socketAddress.getPort());
    }

    /*收取消息*/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (Ping.class.isAssignableFrom(msg.getClass())) {
            ctx.writeAndFlush(new Pong());
        } else {
            if (msg instanceof OtherMessage) {
                OtherMessage message = (OtherMessage) msg;
                log.info("服务端接收到请求：{}", message.getContent());
            }
        }
    }

    /*异常时触发*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("客户端请求异常：{}", cause.getMessage());
    }

    /*客户端断开时触发*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("客户端断开连接：{}:{}", socketAddress.getHostString(), socketAddress.getPort());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state() == IdleState.READER_IDLE) {
                /*读超时*/
                log.info("===服务端===(读超时，关闭channel)");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/
                log.info("===服务端===(WRITER_IDLE 写超时)");
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                log.info("===服务端===(ALL_IDLE 总超时)");
            }
        }
    }

}
