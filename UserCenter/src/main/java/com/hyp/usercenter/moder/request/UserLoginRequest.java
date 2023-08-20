package com.hyp.usercenter.moder.request;

import lombok.Data;

/**
 * 封装前端返回的json，将他们封装成一个返回类
 * 这个类是用户登录所返回的json封装类
 * @author Han
 * @data 2023/8/5
 * @apiNode
 */
@Data
public class UserLoginRequest {
    private String userAccount;
    private String userPassword;
}
