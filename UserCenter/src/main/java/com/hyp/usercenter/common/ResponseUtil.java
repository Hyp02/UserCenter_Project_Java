package com.hyp.usercenter.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 定义通用返回类的通用方法
 *
 * @author Han
 * @data 2023/8/10
 * @apiNode
 */
public class ResponseUtil {
  /**
   * 响应成功
   *
   * @param data 响应数据
   * @param <T>
   * @return 返回响应封装对象
   */
  public static <T> BaseResponse<T> success(T data) {
    return new BaseResponse<T>(20000, data, "success");
  }

  public static <T> BaseResponse<T> success(ErrorCode errorCode, T data) {
    return new BaseResponse<T>(errorCode.getErrorCode(), data, "success");
  }

  public static <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
    return new BaseResponse<T>(errorCode.getErrorCode(), null, errorCode.getMessage(), description);
  }

  public static <T> BaseResponse<T> error(ErrorCode errorCode) {
    return new BaseResponse<T>(errorCode);
  }

  public static <T> BaseResponse<T> error(int code, T data, String message, String description) {
    return new BaseResponse<T>(code, data, message, description);
  }
}
