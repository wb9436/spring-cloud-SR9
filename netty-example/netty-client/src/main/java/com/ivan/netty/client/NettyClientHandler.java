package com.ivan.netty.client;

import com.ivan.netty.common.message.Message;
import com.ivan.netty.common.message.Ping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<Message> {
    private NettyClient nettyClient;

    public NettyClientHandler(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        if (msg.getMsgType() == Message.OTHER) {
            log.info("接收服务端响应为：{}", msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                /*读超时*/
                log.info("===客户端===(READER_IDLE 读超时)");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/
                ctx.writeAndFlush(new Ping());
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("与服务端断开连接 channelInactive:{}", ctx.channel().localAddress());
        nettyClient.reconnect();
    }
}
