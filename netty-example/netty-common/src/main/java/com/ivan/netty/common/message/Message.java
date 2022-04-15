package com.ivan.netty.common.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
public abstract class Message implements Serializable {
    private static final Map<Integer, Class<?>> MESSAGE_CLASSES = new HashMap<>();
    public static final int PING = 100;
    public static final int PONG = 101;
    public static final int OTHER = 102;

    static {
        MESSAGE_CLASSES.put(PING, Ping.class);
        MESSAGE_CLASSES.put(PONG, Pong.class);
        MESSAGE_CLASSES.put(OTHER, OtherMessage.class);
    }

    public abstract int getMsgType();

    public static Class<?> getMessageClass(int messageType) {
        return MESSAGE_CLASSES.get(messageType);
    }

}
