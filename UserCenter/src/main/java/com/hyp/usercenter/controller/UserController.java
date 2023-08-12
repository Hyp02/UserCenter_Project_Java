package com.hyp.usercenter.controller;

import com.hyp.usercenter.common.BaseResponse;
import com.hyp.usercenter.common.ErrorCode;
import com.hyp.usercenter.common.ResponseUtil;
import com.hyp.usercenter.constant.UserConstant;
import com.hyp.usercenter.exception.MyException;
import com.hyp.usercenter.moder.domain.User;
import com.hyp.usercenter.moder.request.UpdateUserRequest;
import com.hyp.usercenter.moder.request.UserLoginRequest;
import com.hyp.usercenter.moder.request.UserRegisterRequest;
import com.hyp.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Han
 * @data 2023/8/5
 * @apiNode
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // 注入service
    @Resource
    private UserService userService;

    /**
     * 注册接口
     *
     * @param userRegisterRequest 注册使用的请求参数的封装类对象
     * @return Login
     */
    @PostMapping("/userRegister")
    private BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验
        if (userRegisterRequest == null) {
            throw new MyException(ErrorCode.NULL_ERROR, "请求参数为null");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new MyException(ErrorCode.NULL_ERROR, "注册信息中有null");
        }

        Long id = userService.userRegister(userAccount, userPassword, userPassword);
        return ResponseUtil.success(id);
    }

    /**
     * 获取用户信息 获取状态
     *
     * @param session
     * @return 返回用户信息，用于给前端一个已经登录的凭证
     */
    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null) {
            throw new MyException(ErrorCode.NOT_LOGIN, "获取登录态时，您可能未登录");
        }
        Long id = user.getId();
        User userById = userService.getById(id);
        // todo 校验用户是否是有效用户
        User safetyUser = userService.getSafetyUser(userById);
        return ResponseUtil.success(safetyUser);

    }

    @PostMapping("/updateUser")
    public BaseResponse<Long> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {

        if (updateUserRequest == null) {
            throw new MyException(ErrorCode.NULL_ERROR, "请求参数可能为null");
        }
        Long id = updateUserRequest.getId();
        String userName = updateUserRequest.getUserName();
        Integer userRole = updateUserRequest.getUserRole();
        // 校验
        if (StringUtils.isAnyBlank(id.toString(),userName,userRole.toString())){
            throw new MyException(ErrorCode.NULL_ERROR,"不能将用户的某项信息修改为空");
        }
        // 将用户名前后空格去掉
        userName = userName.trim();
        Long aLong = userService.updateUser(id, userName, userRole);
        return ResponseUtil.success(aLong);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录所需请求参数的封装类对象
     * @param session          session
     * @return User
     */
    @PostMapping("/userLogin")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpSession session) {
        // 校验
        if (userLoginRequest == null) {
            throw new MyException(ErrorCode.NULL_ERROR, "请求参数可能为null");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new MyException(ErrorCode.NULL_ERROR, "请求参数可能为null");
        }

        User user = userService.userLogin(userAccount, userPassword, session);
        // 修改登录状态
        boolean b = userService.updateUserState(user, UserConstant.ONLINE, session);
        if (!b) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "更新用户状态失败");
        }
        return ResponseUtil.success(user);
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @PostMapping("/outLogin")
    public BaseResponse<String> logout(HttpSession session) {
        if (session == null) {
            return null;
        }
        // 此时操作的用户
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null) {
            throw new MyException(ErrorCode.NULL_ERROR, "未发现登录态");

        }
        // 修改用户在线状态
        boolean b = userService.updateUserState(user, UserConstant.NOT_ONLINE, session);
        if (!b) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "更新用户状态失败");
        }
        // 清除用户的登录状态，例如清除 session、删除用户的会话信息等
        session.invalidate();
        // 返回退出登录成功的消息
        return ResponseUtil.success("Logout successful");
    }

    /**
     * 查询用户
     *
     * @param username
     * @param session
     * @return
     */
    @GetMapping("/queryUsers")
    public BaseResponse<List<User>> queryUser(String username, HttpSession session) {
        // 鉴权
        if (!isAdmin(session)) {
            throw new MyException(ErrorCode.NOT_ADMIN, "没有管理员权限");
        }
        List<User> userList = userService.queryUser(username);
        // 将集合中的每一个user进行脱敏
        List<User> users = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResponseUtil.success(users);
    }

    /**
     * 删除用户
     *
     * @param id
     * @param session
     * @return 是否删除成功 false/true
     */
    @DeleteMapping("/delete/{id}")
    public BaseResponse<Boolean> deleteUser(@PathVariable Long id, HttpSession session) {
        // 鉴权
        if (!isAdmin(session)) {
            throw new MyException(ErrorCode.NOT_ADMIN, "没有管理员权限");
        }
        boolean deleteUser = userService.deleteUser(id);
        return ResponseUtil.success(deleteUser);
    }

    /**
     * 是否为管理员
     *
     * @param session
     * @return false/true
     */
    private boolean isAdmin(HttpSession session) {
        // 角色
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        return userService.judgeRole(user);

    }

}
