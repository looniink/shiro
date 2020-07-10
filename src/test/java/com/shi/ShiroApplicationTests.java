package com.shi;

import com.shi.service.UserService;
import com.shi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Test
	void contextLoads() {
		System.out.println(userService.queryUserByName("weishiqian"));
	}


}
