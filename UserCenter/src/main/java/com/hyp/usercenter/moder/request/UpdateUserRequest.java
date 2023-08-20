package com.hyp.usercenter.moder.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装前端返回的json，将他们封装成一个返回类
 * 这个类是编辑用户所返回的json封装类
 * @author Han
 * @data 2023/8/8
 * @apiNode
 */
@Data
public class UpdateUserRequest implements Serializable {
    private Long id;
    private String userName;
    private Integer userRole;

}
