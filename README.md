我正在「编程导航」和朋友们讨论有趣的话题，你⼀起来吧？
https://t.zsxq.com/11zZlTrgh

# 写代码流程

1. 先做设计
2. 代码实现
3. 持续优化！！！（复用代码、提取公共逻辑 / 常量）

# 初始化前端

## 安装及配置node.js

尽量不要下载最新版的

[node.js16.15.0](https://nodejs.org/download/release/v16.15.0/)

安装时只需要修改安装目录 其他全部 **next**

[**node.js配置yarn npm**](https://blog.csdn.net/zch981964/article/details/124853003?ops_request_misc=%7B%22request%5Fid%22%3A%22165794954316780357293569%22%2C%22scm%22%3A%2220140713.130102334.pc%5Fall.%22%7D&request_id=165794954316780357293569&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~times_rank-4-124853003-null-null.142^v32^new_blog_pos_by_title,185^v2^control&utm_term=nodejs和yarn安装与配置&spm=1018.2226.3001.4187)**（需要提前下载并安装配置好git,不然会出现问题）**

[git安装配置][https://blog.csdn.net/weixin_47638941/article/details/120632890]

**注意** 在配置前需要在node.js安装目录下创建两个文件夹node_global和node_cache,如果出现问题，修改一下文件夹权限并且使用管理员模式打开

## 初始化Ant Design Pro

先在某个盘中创建项目存放位置，我的是存放在`E:\\星球项目`

根据官方文档进行项目初始化

![img](https://cdn.nlark.com/yuque/0/2023/png/34722500/1690960505946-350ca631-084d-49eb-91d4-4994b8a20742.png)

![img](https://cdn.nlark.com/yuque/0/2023/png/34722500/1690961680637-424b0296-af0d-4679-be95-4988b7111fbd.png)

![img](https://cdn.nlark.com/yuque/0/2023/png/34722500/1690960563387-5f89e202-abec-4490-af87-3c81c5c17da1.png)

![img](https://cdn.nlark.com/yuque/0/2023/png/34722500/1690961729489-71b86a9a-10fb-4881-a399-f529c770a105.png)

## 安装项目所需依赖

安装依赖可以不用在cmd中使用`$ cd myapp && npminstall ` 命令

进入webStorm中点击`Terminal`将目录进进入自己的项目输入`yarn`就会自动将项目所需要的依赖下载

![image-20230802160041446](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802160041446.png)

这里依赖安装完成后 在`package.json`文件中找到`start`运行

![image-20230802170352207](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802170352207.png)

## 运行

这里遇到一个离谱的错误

![image-20230802170458115](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802170458115.png)

解决方法就是将这个`index.md`删除

### 效果

![image-20230802171748044](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802171748044.png)

## 添加Umi插件

打开webStorm的控制台输入`yarn add @umijs/preset-ui -D`下载这个插件

- **解决Umi插件区块无法显示问题**

  `打开FastGitHu工具加速`

## 项目各目录功能

![image-20230802194204941](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194204941.png)

存储一些配置文件

![image-20230802194226894](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194226894.png)

存储前端模拟数据

![image-20230802194309975](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194309975.png)

存放静态资源【图标  视频   音频 】

![image-20230802194411366](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194411366.png)

src中存放代码

![image-20230802194434212](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194434212.png)

存放组件

![image-20230802194513844](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802194513844.png)

存放页面

页面和组件的关系：**页面是由多个组件组成的**

![image-20230802205048083](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230802205048083.png)

存放国际化信息

## 项目瘦身

可以将不用的页面或文件删除，但是删除了文件等信息，需要将他的路由也删除

# 初始化后端

## 准备环境

**技术栈：** spring   springMvc   myBatis   myBatis-plus   springBoot  

## 引入框架

使用IDEA的spring Initialize 脚手架创建

选择对应的环境

![image-20230803103443226](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230803103443226.png)

选择要创建的依赖

![image-20230803103526504](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230803103526504.png)

## 测试连接

### 配置连接信息

连接的是yupi这个数据库

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/yupi
    username: root
    password: hyp
    type: com.zaxxer.hikari.HikariDataSource
    # 定义项目名称
  application:
    name: User-Center
```



### 配置myBatis

```yml
# 配置myBatis
myBatis:
  configuration:
    # 开启驼峰命名
    map-underscore-to-camel-case: true
  mapper:
  mapper-locations: classpath:/mapper/*.xml
  type-aliases-package: com.hyp.usercenter.moder

```

### 连接

```java
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
```

连接成功

![image-20230803104108085](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230803104108085.png)

# 数据库表设计

## 创建用户表

字段：

![image-20230803195710247](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230803195710247.png)

```sql
DROP TABLE IF EXISTS user;

create table user
(
    id           bigint auto_increment primary key,
    username     varchar(255)                       null comment '用户昵称',
    UserAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '头像地址',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    column_7     int                                null,
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null
)
    comment '用户表';

```

**注意：**

在设置`createTime `和`updateTime`时候遇到问题，正确定义为：

```sql
createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
```



## 创建数据库表的Java类映射

自动生成：

使用`myBatisX`插件，安装完成后， 就可以自动生成所需要的Mapper接口，Pojo类，service层……等代码

使用：鼠标右键点击对应的数据库表，点击第一个，进去按照所需选择

![image-20230804155233434](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230804155233434.png)

 ## 插件

一键生成这个对象的所有set方法

![image-20230804155406151](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230804155406151.png)

## 测试

测试向数据库表中添加一个`User`对象

```java
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testAddUser(){
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
        System.out.println("id："+user.getId());
        Assertions.assertEquals(true,result);
    }

}
```

运行后，这里遇到一个问题，查看myBatis-plus官网后得知，myBatis-plus默认将数据库的下划线命名和Java的驼峰命名之间的转换是关闭的，默认值是true，我们将它改成false 问题解决

```yaml
# 配置myBatis-plus
myBatis-plus:
  configuration:
    # 开启驼峰命名
    map-underscore-to-camel-case: false
```

# 登录注册后端实现

## 注册

1. 用户在前端输入账户和密码、以及校验码（todo）
2. 校验用户的账户、密码、校验密码，是否符合要求
   1. 非空
   2. 账户长度 **不小于** 4 位
   3. 密码就 **不小于** 8 位吧
   4. 账户不能重复
   5. 账户不包含特殊字符
   6. 密码和校验密码相同
3. 对密码进行加密（**密码千万不要直接以明文存储到数据库中**）
4. 向数据库插入用户数据

**注意：**注册中有两个规则，

 - 账户不能重复
 - 账户中不能包含特殊字符

其中账户不能重复需要连接数据库查询，而账户中不能包含特殊字符要使用正则表达式进行测试，如果账户中包含了特殊字符，我们是不能让他注册成功的，所以也没有必要去看他是否在数据库中重复了，所以要将账户是否包含特殊字符放在账户名是否重复之前，才不浪费性能**【编程技巧】**

检查时，用到了`StringUtils.isAnyBlank()`方法，这个方法是检查传入的字段是否为null

这个方法是`org.apache.commons`库中的一个方法

```xml
 <dependency>
     <groupId>org.apache.commons</groupId>
     <artifactId>commons-lang3</artifactId>
     <version>3.12.0</version>
</dependency>
```

### 注册逻辑

```java
package com.hyp.usercenter.service.impl;
/**
 * @author Han
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-08-03 21:28:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1.校验
        if (StringUtils.isAnyBlank(userAccount, userAccount, checkPassword)) {
            return -1L;
        }
        if (userAccount.length() < 4) {
            return -1L;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1L;
        }
        if (!userPassword.equals(checkPassword)) {
            return -1L;
        }
        // 过滤特殊字符
        String checkRegEx = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(checkRegEx).matcher(userAccount);
        // 如果账户有特殊字符
        if (matcher.find()) {
            return -1L;
        }
        // 账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(userQueryWrapper);
        if (count > 0) {
            return -1L;
        }

        // 密码加密 盐
        final String SALT = "hyp";
        String encryptPWD = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));

        // 保存用户
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPWD);

        boolean insert = this.save(user);
        if (!insert) {
            return -1L;
        }
        return user.getId();
    }
}

```

>  // 密码加密 【盐】
>  final String SALT = "hyp";
>
> 用于加密密码，通俗来说，盐类似于“搅屎棍”，让密码加密更复杂

### 测试

```java
@Test
    void userRegister() {
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
        // 密码不能小于8位
        userAccount = "hyp2";
        userPWD = "123456";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertEquals(-1, register);
        // 账户不能有特殊字符
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
        // 成功注册一个
        userPWD = "123123123";
        checkPWD = "123123123";
        register = userService.userRegister(userAccount, userPWD, checkPWD);
        Assertions.assertTrue(register != -1);

    }
```

**注意：** `userMapper.insert(user)`myBatis-plus 中这个方法的返回值**不是受影响的行数**，是**数据库表中的第几行收到影响**

## 登录

### 登录逻辑

1. 校验用户账户和密码是否合法

   1. 非空
   2. 账户长度不小于 4 位
   3. 密码就不小于 8 位
   4. 账户不包含特殊字符
   5. 校验完成符合登录要求，将用户转态修改为登录状态

2. 校验密码是否输入正确，要和数据库中的密文密码去对比

3. 用户信息**脱敏**，隐藏敏感信息，防止数据库中的字段泄露

4. 我们要记录用户的登录态（session），将其存到服务器上（用后端 SpringBoot 框架封装的服务器 tomcat 去记录）

   cookie

5. 返回**脱敏**后的用户信息


**注意：**在进行第四步时，鱼皮使用的是`HttpServletRequest`来获取`HttpSession`对象来保存用户的登录态，而我使用的是`HttpSession`,

他们之间的区别

![image-20230804231344010](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230804231344010.png)

后面将代码中返回的null 统一封装为异常类

---

登陆中有一个问题，如果这个账户的状态是已经逻辑删除的（相当于封号或注销）那么这个账户是无法从数据库中查询出来的，说明不能登录，所以要设置一个检查是否被逻辑删除的功能，而myBatis-plus提供了这个逻辑删除功能

> 取自myBatis-plus官网
>
> ![image-20230804233531046](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230804233531046.png)

```java
//实体类中
	@TableLogic
    private Integer isDelete;
```

```yaml
# yaml配置文件中
    mybatis-plus:
      global-config:
        db-config:
          logic-delete-field: isDelete # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
          logic-delete-value: 1 # 逻辑已删除值(默认为 1)
          logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

### 登录接口设计

接受参数：用户账户、密码

请求类型：POST 

请求体：JSON 格式的数据

> 请求参数很长时不建议用 get

返回值：用户信息（ **脱敏** ）

----

**开发规范**

> 在实现登录接口设计时，要给service层方法传递前端传来的请求参数
>
> 在方法签名中直接书写所需要的参数是不规范的，我们要设计一个专门用来存放请求参数的类
>
> 这个类就叫做`UserRegisterRequest`

```java
@Data
public class UserRegisterRequest implements Serializable {
    // 请求参数
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
```

**这里实现了Serializable接口，是保证前端发过来请求参数后，这些请求参数能够序列化为这个类的对象的一种保证**

但是在controller中有@RequsetBody注解标注，所以这个可以不用实现Serializable接口

### 插件

**这里用到了一个插件，在方法传入参数的时候，一个一个手打很麻烦，下载一个 `Auto Filling Java Call  Arguments`插件用来自动填充参数**

# 用户管理

## 权限判定

在数据库表中加入`角色 userRole`字段

0 -- 表示普通用户

1 -- 表示管理员

> 角色判断 在UserService中

```java
 @Override
    public boolean judgeRole(User user) {
        if (user == null) {
            return false;
        }
        Integer role = user.getUserRole();
        // 是普通用户
        if (role == UserConstant.DEFAULT_ROLE){
            return false;
        }
        return true;
    }
```

> 在Controller层中可封装一个方法用来判断是不是管理员
>
> 这样可以防止重复冗余代码

```java
/**
     * 是否为管理员
     * @param session
     * @return
     */
    private boolean isAdmin(HttpSession session){
        // 角色
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        boolean isAdmin = userService.judgeRole(user);
        return isAdmin;
    }
```

## 查询用户是否在线

- 在登录成功后，将数据库表中userState段设置为1
- 在退出登录后，将数据库表中userState字段设置为0

## 退出登录

将用户的登录态清空并且将这个用户的userState字段修改为0

```java
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
            return false;
        }
        QueryWrapper<User> w = new QueryWrapper<>();
        w.eq("id", loginUser.getId());
        loginUser.setUserStatus(state);
        // 修改数据库状态信息
        int update = userMapper.update(loginUser, w);
        if (update > 0) {
            return true;
        }
        return false;

    }
```

```java
 @PostMapping("/outLogin")
    public String logout(HttpSession session) {
        // 此时操作的用户
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        // 修改在线状态
        userService.updateUserState(user, UserConstant.NOT_ONLINE, session);
        // 清除用户的登录状态，例如清除 session、删除用户的会话信息等
        session.invalidate();
        // 返回退出登录成功的消息
        return "Logout successful";
    }
```



## 查询用户

接口设计关键：必须鉴权！！！

1. 查询用户（允许根据用户名查询）

   查询用户比较简单，这里用到的是根据用户名查询，如果没有传入用户名，那么将返回数据库中的所有信息**【注意权限】**

   ` wrapper.like("username", username)`模糊查询

   ```java
    @Override
       public List<User> queryUser(String username) {
           QueryWrapper<User> wrapper = new QueryWrapper<>();
           // 条件为null
           if (StringUtils.isAnyBlank(username)){
               // 返回所有
               return userMapper.selectList(wrapper);
           }
           wrapper.like("username", username);
           return userMapper.selectList(wrapper);
       }
   ```

   **注意：**

   在管理员查询用户后，要返回的还是脱敏用户，所以为了代码不冗余，我们在service层中定义`获取脱敏用户方法`

- 方法逻辑：
  - 传入一个originUser
  - 在这个方法中创建一个`safetyUser`User对象
  - 定义可以安全返回的safetyUser对象的set方法，
  - 把originUser对象能返回的数据get出来，放在set方法中
  - 最终返回这个安全对象`safetyUser`

```java
 /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser){
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
```

在controller层中返回安全对象

![image-20230805181907988](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230805181907988.png)

## 删除用户

删除用户与查询用户逻辑相同，**但是还是要注意权限问题**

```java
 @PostMapping("/delete")
    public boolean deleteUser(@RequestBody Long id,HttpSession session) {

        if (!isAdmin(session)) {
            return false;
        }
        return userService.deleteUser(id);
    }
```

## 编辑用户

逻辑

- 拿到要编辑的用户的id，按照id修改
- 从数据库中用id取出来这个用户
- 使用这个用户的set方法，将要修改的值修改
- 将修改的新用户传入数据库，更新

```java
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
            return null;
        }
        return id;
    }
```

```java
 @PostMapping("/updateUser")
    public Long updateUser(@RequestBody UpdateUserRequest updateUserRequest) {

        if (updateUserRequest == null) {
            return null;
        }
        Long id = updateUserRequest.getId();
        String userName = updateUserRequest.getUserName();
        Integer userRole = updateUserRequest.getUserRole();
        Long aLong = userService.updateUser(id,userName,userRole);
        return aLong;
    }
```



## 规范代码

为了使代码更规范，

- controller层写控制层代码，尽量不要包含业务逻辑
- 将用到的常量数据封装为一个constant接口
- 在这个接口中包含的常量用大写字母表示

如：

![image-20230805182329304](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230805182329304.png)

- 在controller层，要用到很多请求参数，将这些请求参数可以封装为一个请求参数类，在方法签名中写参数类对象就可以了

  - 例如登录的时候，我们要用到userAccount、userPassword、checkPassword等前端发来的请求参数

  - 我们可以将些请求参数封装为UserLoginRequest类，

    - 这个类中有userAccount、userPassword字段,并且有getSet方法

    - 实现Serializable接口，用来序列化

      ![image-20230805183057059](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230805183057059.png)

# 退出登录

## 修改用户登录信息

拿到已经登录的用户，获取这个用户的id,**登录时将状态改为1 退出时将转态改为0，**

```java
 /**
     * 修改登录或退出登录时的用户状态
     * @param user 此时操作的用户
     * @param state 用户状态，0离线 1在线
     * @return 修改成功返回true,失败返回false
     */
    @Override
    public boolean updateUserState(User user, Integer state,HttpSession session) {
        User loginUser =(User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (loginUser == null){
            return false;
        }
        QueryWrapper<User> w = new QueryWrapper<>();
        w.eq("id", loginUser.getId());
        loginUser.setUserStatus(state);
        // 修改数据库状态信息
        int update = userMapper.update(loginUser, w);
        if (update > 0){
            return true;
        }
        return false;

    }
```

## 接口

```java
/**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("/outLogin")
    public String logout(HttpSession session) {
        // 此时操作的用户
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        // 修改在线状态
        userService.updateUserState(user,UserConstant.NOT_ONLINE,session);
        // 清除用户的登录状态，例如清除 session、删除用户的会话信息等
        session.invalidate();
        // 返回退出登录成功的消息
        return "Logout successful";
    }
```





# 获取用户态

登录成功后，后端会将登录用户保存到session中并且返回登录用户信息。登录成功后前端需要知道登录的用户的所有脱敏信息。所以要定义一个接口来给前端返回这个数据;

根据这个已经登录的用户的用户id从数据库中查询信息返回给前端，

**为什么不从session中直接返回呢？**

这是因为session中存储的用户是一个**"假"**用户，如果我们在数据库中修改了这个用户的信息，返回的信息就不对了，所以这里要查一次表

这个接口定义为`currentUser`

```java
 /**
     * 获取用户信息 获取状态
     * @param session
     * @return 返回用户信息，用于给前端一个已经登录的凭证
     */
    @GetMapping("/currentUser")
    public User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null) {
            return null;
        }
        Long id = user.getId();
        User userById = userService.getById(id);
        // todo 校验用户是否是有效用户
        return userService.getSafetyUser(userById);

    }
```

# 后端代码优化

- 通用返回对象

​	定义通用对象 是一个泛型类

- 构成
  -  响应码 code
  - 响应数据，为泛型类型
  - 响应码信息【也就是说这个响应笼统的划分为哪一类，是错误还是提示】
  - 这个响应的详细描述
- 定义各种构造函数
- 用法
  - 在controller层返回数据时，将返回类型封装为这个通用返回对象

如登录接口

- 其中将登录接口的返回类型封装为BaseResponse类型，
- 最终返回的是这个类的封装类型
- ResponseUtil类中定义了各类通用方法
- 如：success(T data) 

```java
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
```

- 封装全局异常处理
  - 定义一个错误码枚举类
  - 这个枚举类中有自定义项目状态码
  - 每一个状态码对应一类提示信息


![image-20230811151901939](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230811151901939.png)

- 定义自定义异常类`MyException`

  - 继承Exception类，自记在原有类结构上 添加code(状态码)description（错误信息描述）字段

  - 定义一些构造方法，和**错误码枚举类**结合使用

  - 如：

    - ```
      public MyException(ErrorCode errorCode, String description) {
          super(errorCode.getMessage());
          this.code = errorCode.getErrorCode();
          this.description = description;
      }
      ```

  - 在项目要抛出异常的地方进行异常捕获

  - ![image-20230811152325482](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230811152325482.png)

- 全局异常处理器

  - 集中处理项目抛出的异常，返回项目发生异常的json对象给前端
  - ![image-20230811152733686](C:\Users\Han\AppData\Roaming\Typora\typora-user-images\image-20230811152733686.png)

- 结果,这个示例是 请求注册接口，故意将注册信息的账号长度填写1位(要求是不能低于4位)

```json
{
    "code":40000,
    "data":null,	
    "message":"请求参数错误",
    "description":"账号过短"
}
```

- 注册已经注册过的用户

```json
{
    "code"33060
    "data": null
    "description": "数据库中发现重复用户"
    "message": "数据库操作错误"
}
```
