package com.ivan.common.exception;

import com.ivan.common.result.ResultCode;
import lombok.Data;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }

}
