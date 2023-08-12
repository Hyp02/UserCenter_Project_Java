package com.hyp.usercenter.constant;

/**
 * @author Han
 * @data 2023/8/5
 * @apiNode
 */
public interface UserConstant {
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";
    /**
     * 盐值 用于混淆密码
     */
    String SALT = "hyp";
    /**
     * 普通用户
     */
    Integer DEFAULT_ROLE = 0;
    /**
     * 管理员
     */
    Integer ADMIN_ROLE = 1;
    /**
     * 用户在线状态
     */
    Integer ONLINE = 1;
    /**
     * 用户离线状态
     */
    Integer NOT_ONLINE = 0;
}
