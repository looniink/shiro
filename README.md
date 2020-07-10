# shiro
:triangular_flag_on_post: A project to practice Shiro，学习shiro时的小demo
## 1.shiro的基本概念
- application code：应用程序代码，代表shiro的程序入口。将用户的数据用shiro的api传递到shiro，由shiro处理数据。
- subject：每一个subject代表一个用户。用shiro对用户数据进行封装，把数据给token（账号和密码生成令牌）
- securitymanager：安全中心，所有的数据都会经过安全中心，对用户进行管理
- realm：域，连接的数据源头，可以是数据库，也可以是文件
![shiro][1]

## 2. shiro快速开始
### 2.1 官方quickstart里面的重要方法
- 获取当前用户对象subject
- 通过当前用户拿到session
- 判断当前用户是否被认证
- 获取当前用户的认证
- 判断该用户是否拥有某个角色
- 获取当前用户的权限
![shiro架构图][2]

## 3. springboot整合shiro

### 3.1 具体操作
- 创建项目并编写Controller进行测试
- 导入shiro-spring依赖
- 编写shiroconfig（三大对象）


## 4. shiroconfig的编写

### 4.1 三大对象
- ShiroFilterFactoryBean
- DefaultWebSecurityManager
- 自定义realm对象

### 4.2 自定义realm对象
- 创建UserRealm对象继承AuthorizingRealm方法
- 实现认证与授权方法
- 在shiroconfig中注入spring

### 4.3 创建DefaultWebSecurityManager管理器，将realm丢进去与之关联
### 4.4 创建ShiroFilterFactoryBean管理模式，将DefaultWebSecurityManager管理器丢进去，设置安全管理器
### 4.5 编写add与update界面

> 正确的Themeleleaf命名空间才会有th标签的提示

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

## 5. shiro实现登录拦截

### 5.1 添加shiro的内置过滤器
- anon:无需认证就可以访问
- authc:必须认证才能访问
- user：必须拥有记住我功能才能访问
- perms：拥有对某个资源的权限才能访问
- role：拥有某个角色权限才能访问
> 配置过滤器时支持通配符

### 5.2 设置登录的请求
- 被拦截以后跳转到登录页面login

## 6. shiro实现用户认证
- 获取当前用户
- 封装当前用户登录信息，用户名密码作为token
- 执行登录方法，传入token令牌
- 在realm里面获取令牌信息对用户名密码进行判断
- 登录没有异常则登录成功

> authc认证方法里面自带了多种异常，用户名错误、密码错误etc
> 用户名自己判断，密码shiro进行判断
> 自定义数据模拟测试

## 7. shiro整合mybatis
- 连接数据库配置yml文件
- mapper、mapper.xml、service、serviceImpl  （Test测试）
- 测试成功后将realm中手动模拟的数据跟换为数据库中的数据


## 8. shiro实现请求授权
> type=Unauthorized, status=401（401表示未授权错误）
> 没有授权则无妨访问网页，例如登录成功后才能访问其他网页
> 根据数据库中不同用户的权限信息访问不同的功能

### 8.1 授权操作（shiroconfig文件）
- 与拦截相似put是采用perms，表示拥有对某个资源的权限才能访问
- 编写未授权Controller，没有授权时返回提示信息
- 在userrealm里面可以添加需要授权的信息，授权过后就可以访问了（手动模拟权限信息）
- 在数据库中创建perms属性，作为权限信息（从数据库获取权限信息）
- 从数据库中获取权限信息，设置用户的权限

## 9. shiro整合Themeleleaf（与springsecurity的包类似一样）
- 编写themele-shiro pom文件
- shiroconfig配置shirodialect，整合Themeleleaf与shiro
- 结合后进行判断有那个权限就显示那个按钮

> 个人感觉还是springsecurity好用一些，但是shiro的可定制化比较高
> shirodemo代码地址：


  [1]: https://static.looni.ink/article/shiro/shiro_1.png#vwid=549&vhei=271
  [2]: https://static.looni.ink/article/shiro/shiro_2.png#vwid=521&vhei=356
