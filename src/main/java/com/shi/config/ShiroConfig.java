package com.shi.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	//ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		//添加shiro的内置过滤器
		/*
			anon:无需认证就可以访问
			authc:必须认证才能访问
			user：必须拥有记住我功能才能访问
			perms：拥有对某个资源的权限才能访问
			role：拥有某个角色权限才能访问
		 */
		LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();


		//授权，正常情况未授权会跳转到未授权页面
		filterMap.put("/user/add", "perms[user:add]");   //权限 表示必须带有user：add才能进入
		filterMap.put("/user/update", "perms[user:update]");//有更新权限才可以访问

		//拦截
		//filterMap.put("/user/add", "authc");
		//filterMap.put("/user/update", "authc");
		filterMap.put("/user/*", "authc");      //支持通配符


		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		//设置登录的请求（被拦截过后会跳转到登录界面）
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		//设置未授权请求(未授权会跳转到该页面)
		shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
		return shiroFilterFactoryBean;
	}

	//DefaultWebSecurityManager
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		//关联userRealm
		defaultWebSecurityManager.setRealm(userRealm);
		return defaultWebSecurityManager;
	}

	//自定义realm对象
	@Bean(name = "userRealm")   //spring中直接绑定的就是方法名  ，也可以直接用方法名不用name进行绑定@
	public UserRealm userRealm() {
		return new UserRealm();
	}

	//整合shirodialect
	@Bean
	public ShiroDialect getshiroDialect() {
		return new ShiroDialect();
	}
}
