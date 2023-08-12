package com.hyp.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.usercenter.moder.domain.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Han
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-08-03 21:28:52
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 验证密码
     * @return 新用户id
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 返回用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpSession session);

    /**
     * 查询用户
     *
     * @param username
     * @return 所有用户list
     */
    List<User> queryUser(String username);

    /**
     * 逻辑删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(Long id);

    /**
     * 用户是否是管理员
     *
     * @param user
     * @return ture/false
     */
    boolean judgeRole(User user);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return 脱敏用户
     */
    User getSafetyUser(User originUser);

    /**
     * 修改登录或退出登录时的用户状态
     *
     * @param user
     * @param state 用户状态，0离线 1在线
     * @return 修改成功返回true, 失败返回false
     */
    boolean updateUserState(User user, Integer state, HttpSession session);

    /**
     * 编辑后修改用户
     * @param user
     * @return 返回修改的用户id
     */
    Long updateUser( Long id,String userName,Integer userRole);

}
