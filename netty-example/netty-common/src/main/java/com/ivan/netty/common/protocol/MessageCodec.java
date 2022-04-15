package com.ivan.netty.common.protocol;

import com.ivan.netty.common.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //1、4字节指令类型
        out.writeInt(msg.getMsgType());
        byte[] data = ProtostuffUtil.serializer(msg);
        //2、正文长度
        out.writeInt(data.length);
        //3、消息正文
        out.writeBytes(data);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        //1、指令类型
        int msgType = in.readInt();
        //2、正文长度
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
        }
        //3、消息正文
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object msg = ProtostuffUtil.deserializer(data, Message.getMessageClass(msgType));
        out.add(msg);
    }
}
