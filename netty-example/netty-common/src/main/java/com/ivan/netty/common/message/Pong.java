package com.ivan.netty.common.message;

import java.io.Serializable;

public class Pong extends Message implements Serializable {
    @Override
    public int getMsgType() {
        return PONG;
    }
}
