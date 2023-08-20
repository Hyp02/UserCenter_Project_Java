package com.hyp.usercenter.exception;

import com.hyp.usercenter.common.ErrorCode;

/**
 * 定义业务异常类,添加自定义状态码和异常描述信息
 * 定义多个异常构造方法，便于灵活的返回异常信息
 * @author Han
 * @data 2023/8/10
 * @apiNode
 */
public class MyException extends RuntimeException {
    /**
     * 异常的状态码
     */
    private final int code;
    /**
     * 异常的描述
     */
    private final String description;

    public MyException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    /**
     * @param errorCode
     */
    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getErrorCode();
        this.description = errorCode.getDescription();

    }

    public MyException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getErrorCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
