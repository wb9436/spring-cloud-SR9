package com.ivan.common.exception;

import com.ivan.common.log.ExceptionUtil;
import com.ivan.common.result.R;
import com.ivan.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author: WB
 * @version: v1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * -------- 通用异常处理方法 --------
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e)); // 调用异常处理方法
        return R.error(); // 通用异常结果
    }

    /**
     * -------- 指定异常处理方法 --------
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R error(NullPointerException e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e)); // 调用异常处理方法
        return R.setResult(ResultCode.NULL_POINT);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public R error(IndexOutOfBoundsException e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e)); // 调用异常处理方法
        return R.setResult(ResultCode.HTTP_CLIENT_ERROR);
    }

    /**
     * -------- 自定义定异常处理方法 --------
     **/
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public R error(GlobalException e) {
//        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e)); // 调用异常处理方法
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
