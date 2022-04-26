package com.ivan.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
public class R {
    private int code;
    private String message;
    private Map<String, Object> data;

    private R(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = new HashMap<>();
    }

    public static R ok() {
        return new R(ResultCode.OK);
    }

    public static R error(ResultCode resultCode) {
        return new R(resultCode);
    }

    public static R result(ResultCode resultCode) {
        return new R(resultCode);
    }

    public R data(String key, Object data) {
        this.data.put(key, data);
        return this;
    }

    public R msg(String message) {
        this.message = message;
        return this;
    }
}
