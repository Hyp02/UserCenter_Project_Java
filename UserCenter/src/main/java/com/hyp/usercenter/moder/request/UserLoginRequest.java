package com.hyp.usercenter.moder.request;

import lombok.Data;

/**
 * @author Han
 * @data 2023/8/5
 * @apiNode
 */
@Data
public class UserLoginRequest {
    private String userAccount;
    private String userPassword;
}
