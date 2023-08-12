package com.hyp.usercenter.exception;

import com.hyp.usercenter.common.BaseResponse;
import com.hyp.usercenter.common.ErrorCode;
import com.hyp.usercenter.common.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 处理这个项目多有抛出的异常
 * 将异常信息以json数据发送给前端
 *
 * @author Han
 * @data 2023/8/11
 * @apiNode
 */
// 集中异常处理，返回异常的json对象给前端
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 集中处理自定义项目异常
     *
     * @param e MyException 异常类型
     * @return
     */
    @ExceptionHandler(MyException.class)
    public BaseResponse myExceptionHandler(MyException e) {
        log.error("myException " + e.getMessage(), e);
        return ResponseUtil.error(e.getCode(), null, e.getMessage(), e.getDescription());
    }

    /**
     * 集中处理RuntimeException异常
     *
     * @param e runtimeException异常类型
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e) {
        log.error("runtimeException", e);
        return ResponseUtil.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }


}
