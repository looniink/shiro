package com.shi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

	@RequestMapping({"/", "/index"})     //可以传数组
	public String index(Model model) {
		model.addAttribute("msg", "hello shiro!");
		return "index";
	}

	@RequestMapping("/user/add")
	public String add() {
		return "user/add";
	}

	@RequestMapping("/user/update")
	public String update() {
		return "user/update";
	}

	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login")
	public String login(String username, String password, Model model) {
		//获取当前的用户，也就是获取subject
		Subject subject = SecurityUtils.getSubject();
		//封装用户的信息作为token令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);       //执行登录方法如果没有异常则表明登录成功
			return "index";             //登录成功返回首页
		} catch (UnknownAccountException e) { //用户名不存在
			model.addAttribute("msg", "用户名错误");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码错误");
			return "login";
		}
	}

	@RequestMapping("/noauth")
	@ResponseBody
	public String unauthorized() {
		return "未经授权无法访问";
	}
}


