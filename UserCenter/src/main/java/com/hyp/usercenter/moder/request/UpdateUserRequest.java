package com.hyp.usercenter.moder.request;

import lombok.Data;

import java.io.Serializable;

/**
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
