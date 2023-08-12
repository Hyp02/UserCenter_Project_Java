package com.hyp.usercenter.moder.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Han
 * @data 2023/8/5
 * @apiNode
 */
@Data
public class UserRegisterRequest implements Serializable {
    // 请求参数
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
