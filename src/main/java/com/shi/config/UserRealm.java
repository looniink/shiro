package com.shi.config;

import com.shi.entity.User;
import com.shi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;

//自定义UseerRealm  extends  AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
	@Autowired
	UserService userService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行了=》授权AuthorizationInfo");
		//为用户增加授权所需要的信息
		SimpleAuthorizationInfo Info = new SimpleAuthorizationInfo();
		//Info.addStringPermission("user:add");   //添加完授权信息过后就可以访问了

		//获取到当前登录的对象
		Subject subject = SecurityUtils.getSubject();
		User currentUser = (User) subject.getPrincipal();

		//设置当前用户的权限
		Info.addStringPermission(currentUser.getPerms());

		return Info;
	}


	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行了=》认证AuthenticationInfo");

		//拿到token令牌，token是全局通用的
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;

		//用户名密码从数据库中获取
		User user = userService.queryUserByName(userToken.getUsername());

		if (user == null) {         //没有查询到该用户
			return null;
		}

		//密码用shiro进行判断
		return new SimpleAuthenticationInfo(user, user.getPwd(), "");
	}
}
