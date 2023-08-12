package com.hyp.usercenter.service;

import java.util.Date;

import com.hyp.usercenter.moder.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Han
 * @data 2023/8/3
 * @apiNode
 */
@SpringBootTest
@Slf4j
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("hyp");
        user.setUserAccount("123");
        user.setAvatarUrl("https://cn.bing.com/images/search?q=%E5%A4%B4%E5%83%8F%E8%BF" +
                "%9E%E6%8E%A5&FORM=IQFRBA&id=B68CEDF76C467F34FAB1F49B66B349CC297F002A");
        user.setGender(0);
        user.setUserPassword("");
        user.setPhone("");
        user.setEmail("");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        boolean result = userService.save(user);
        System.out.println("id：" + user.getId());
        Assertions.assertEquals(true, result);


    }

    /**
     * 测试注册功能
     */
    @Test
    void userRegister() {
        log.info("测试类运行");
        // 密码不能为空
        String userAccount = "hyp2";
        String userPWD = "";
        String checkPWD = "123456";
        Long register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 账户不能小于4位
        userAccount = "jj";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 密码小于8位
        userAccount = "hyp2";
        userPWD = "123456";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 账户有特殊字符
        userAccount = "h$pp";
        userPWD = "123123123";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 账户不能重复
        userAccount = "hypp";
        userPWD = "123123123";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 密码和校验密码要相同
        userAccount = "hyp2";
        checkPWD = "12345678";
        userPWD = "1232311232";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 注册一个
        userPWD = "123123123";
        checkPWD = "123123123";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertTrue(register != -1);

    }

    @Test
    void userLogin() {
    }

    @Test
    void queryUser() {
    }

    @Test
    void deleteUser() {
        System.out.println(userService.deleteUser(1L));

    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("nihao");
        //user.setUserAccount("");
        //user.setAvatarUrl("");
        //user.setGender(0);
        //user.setUserPassword("");
        //user.setPhone("");
        //user.setEmail("");
        //user.setUserStatus(0);
        //user.setCreateTime(new Date());
        //user.setUpdateTime(new Date());
        //user.setIsDelete(0);
        //user.setUserRole(0);
        //Long aLong = userService.updateUser(user);
        //boolean result = true;
        //if (aLong <= 0 ){
        //    result=false;
        //}
        //Assertions.assertTrue(result);

    }
}