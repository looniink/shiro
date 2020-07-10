package com.shi.service;

import com.shi.entity.User;
import com.shi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User queryUserByName(String name) {
		return userMapper.queryUserByName(name);
	}
}
