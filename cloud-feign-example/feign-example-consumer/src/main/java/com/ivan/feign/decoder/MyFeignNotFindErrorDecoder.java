package com.ivan.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * 自定义404异常处理类（decode404=false触发）
 *
 * @author: WB
 * @version: v1.0
 */
@Slf4j
public class MyFeignNotFindErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if (status == HttpStatus.NOT_FOUND.value()) {
            log.error("{} 请求的资源不存在，请确认请求地址是否正确！", methodKey);
            return new Exception(methodKey + "请求的资源地址异常");
        }
        return null;
    }
}
