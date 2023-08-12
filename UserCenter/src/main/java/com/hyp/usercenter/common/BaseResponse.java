package com.hyp.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回对象
 * @author Han
 * @data 2023/8/10
 * @apiNode
 */
@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 业务状态码
     */
    private int code;
    /**
     * 要返回的data（数据）
     */
    private T data;
    /**
     * 状态码解释（信息）
     */
    private String message;
    /**
     * 描述
     */
    private String description;

    /**
     * @param code
     * @param data
     * @param message
     */

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getErrorCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
