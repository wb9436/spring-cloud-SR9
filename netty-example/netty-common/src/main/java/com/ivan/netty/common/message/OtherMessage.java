package com.ivan.netty.common.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
public class OtherMessage extends Message implements Serializable {
    private String content;

    public OtherMessage() {
    }

    public OtherMessage(String content) {
        this.content = content;
    }

    @Override
    public int getMsgType() {
        return OTHER;
    }

}
