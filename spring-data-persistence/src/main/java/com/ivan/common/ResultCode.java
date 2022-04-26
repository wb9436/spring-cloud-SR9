package com.ivan.common;

import lombok.Getter;

/**
 * @author: WB
 * @version: v1.0
 */
@Getter
public enum ResultCode {
    OK(2000, "成功"),
    CREATE_TABLE_OK(2001, "创建牌桌成功"),
    CREATE_TABLE_ERROR(3001, "创建牌桌失败"),
    JOIN_TABLE_OK(2002, "加入牌桌成功"),
    JOIN_TABLE_ERROR(3002, "加入牌桌失败"),
    ;
    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
