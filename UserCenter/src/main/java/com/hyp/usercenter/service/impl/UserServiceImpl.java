package com.hyp.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.usercenter.common.ErrorCode;
import com.hyp.usercenter.constant.UserConstant;
import com.hyp.usercenter.exception.MyException;
import com.hyp.usercenter.mapper.UserMapper;
import com.hyp.usercenter.moder.domain.User;
import com.hyp.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户service
 * 将遇到的异常全部抛出
 * @author Han
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-08-03 21:28:52
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userAccount, checkPassword)) {
            throw new MyException(ErrorCode.NULL_ERROR,"参数为空");
        }
        if (userAccount.length() < 4) {
            throw new MyException(ErrorCode.PARAM_ERROR,"账号过短");

        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new MyException(ErrorCode.PARAM_ERROR,"密码长度过短");

        }
        // 密码和校验密码
        if (!userPassword.equals(checkPassword)) {
            throw new MyException(ErrorCode.PARAM_ERROR,"两次密码输入不一致");

        }
        // 过滤特殊字符
        String checkRegEx = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(checkRegEx).matcher(userAccount);
        // 如果账户有特殊字符
        if (matcher.find()) {
            throw new MyException(ErrorCode.PARAM_ERROR,"账户名不符合规范");

        }
        // 账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(userQueryWrapper);
        if (count > 0) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "数据库中发现重复用户");
        }

        // 密码加密
        String encryptPWD = DigestUtils.md5DigestAsHex((UserConstant.SALT + userPassword).getBytes(StandardCharsets.UTF_8));

        // 保存用户
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPWD);

        boolean insert = this.save(user);
        if (!insert) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "保存用户失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpSession session) {
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        // 匹配特殊字符
        String checkRegEx = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(checkRegEx).matcher(userAccount);
        if (matcher.find()) {
            throw new MyException(ErrorCode.PARAM_ERROR, "您输入的用户名包含特殊字符");

        }
        if (userAccount.length() < 4 || userPassword.length() < 8) {
            throw new MyException(ErrorCode.PARAM_ERROR, "参数长度不符合规定");

        }
        String encryptPWD = DigestUtils.md5DigestAsHex((UserConstant.SALT + userPassword).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        userQueryWrapper.eq("userPassword", encryptPWD);
        User user = userMapper.selectOne(userQueryWrapper);
        //用户不存在
        if (user == null) {
            log.info("user login failed,userAccount cannot match userPassword");
            throw new MyException(ErrorCode.DATABASE_ERROR, "用户名或密码不正确");

        }
        // 脱敏
        User safetyUser = getSafetyUser(user);
        // 记录用户的登录态
        session.setAttribute(UserConstant.USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    @Override
    public List<User> queryUser(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 条件为null
        if (StringUtils.isAnyBlank(username)) {
            // 返回所有
            return userMapper.selectList(wrapper);
        }
        wrapper.like("username", username);
        return userMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            throw new MyException(ErrorCode.PARAM_ERROR, "请求参数为空");

        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        int delete = userMapper.delete(wrapper);
        if (delete == 0) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "删除用户失败");
        }
        return true;
    }

    @Override
    public boolean judgeRole(User user) {
        if (user == null) {
            throw new MyException(ErrorCode.PARAM_ERROR, "请求参数为空");

        }
        Integer role = user.getUserRole();
        // 是普通用户
        if (role == UserConstant.DEFAULT_ROLE) {
            throw new MyException(ErrorCode.NOT_ADMIN, "不是 管理员");

        }
        return true;
    }

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            throw new MyException(ErrorCode.PARAM_ERROR, "请求参数为空");

        }
        // 脱敏
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setUserRole(originUser.getUserRole());
        return safetyUser;
    }

    /**
     * 修改登录或退出登录时的用户状态
     *
     * @param user
     * @param state 用户状态，0离线 1在线
     * @return 修改成功返回true, 失败返回false
     */
    @Override
    public boolean updateUserState(User user, Integer state, HttpSession session) {
        User loginUser = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (loginUser == null) {
            throw new MyException(ErrorCode.PARAM_ERROR, "请求参数为空");
        }
        QueryWrapper<User> w = new QueryWrapper<>();
        w.eq("id", loginUser.getId());
        loginUser.setUserStatus(state);
        // 修改数据库状态信息
        int update = userMapper.update(loginUser, w);
        if (update <= 0) {
            throw new MyException(ErrorCode.DATABASE_ERROR, "更新用户状态失败");
        }
        return true;

    }

    /**
     *
     * @param id 要修改的用户id
     * @param userName 修改为
     * @param userRole 修改为
     * @return 修改的用户id
     */
    @Override
    public Long updateUser(Long id,String userName,Integer userRole) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("id",id );
        User user = userMapper.selectOne(wrapper);
        user.setUsername(userName);
        user.setUserRole(userRole);
        int update = userMapper.update(user, wrapper);
        if (update <= 0){
            throw new MyException(ErrorCode.DATABASE_ERROR, "修改用户信息失败");
        }
        return id;
    }
}