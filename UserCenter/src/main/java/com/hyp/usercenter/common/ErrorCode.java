package com.hyp.usercenter.common;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * 状态码 枚举类
 * 为这个项目提供状态码
 * @author Han
 * @data 2023/8/10
 * @apiNode
 */

public enum ErrorCode {

    SYSTEM_ERROR(000001,"系统错误",""),
    SUCCESS(20000, "success", ""),
    PARAM_ERROR(40000, "请求参数错误", ""),
     NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NOT_ADMIN(40101, "无权限", ""),
    DATABASE_ERROR(33060,"数据库操作错误","");

    /**
     * 异常code
     */
    private final int errorCode;
    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误描述
     */
    private final String description;

    private ErrorCode(int errorCode, String message, String description) {
        this.errorCode = errorCode;
        this.message = message;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
