package com.ivan.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
public class R {
    private Integer code;
    private String message;
    private Boolean success;
    private Map<String, Object> data;

    private R() {
        this.data = new HashMap<>();
    }

    // 通用返回成功
    public static R ok() {
        R r = new R();
        r.setSuccess(ResultCode.SUCCESS.getSuccess());
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    // 通用返回失败，未知错误
    public static R error() {
        R r = new R();
        r.setSuccess(ResultCode.UNKNOWN_ERROR.getSuccess());
        r.setCode(ResultCode.UNKNOWN_ERROR.getCode());
        r.setMessage(ResultCode.UNKNOWN_ERROR.getMessage());
        return r;
    }

    // 设置结果，形参为结果枚举
    public static R setResult(ResultCode result) {
        R r = new R();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**
     * ------------使用链式编程，返回类本身-----------
     **/

    // 自定义返回数据
    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

}
