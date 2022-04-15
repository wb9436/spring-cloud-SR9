package com.ivan.netty.common.message;

import java.io.Serializable;

public class Ping extends Message implements Serializable {
    @Override
    public int getMsgType() {
        return PING;
    }
}
