package com.example.demo;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.req.UserCreateReqVo;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;


@RunWith(SpringRunner.class)
@MapperScan("com.example.demo.dao")
@SpringBootTest(webEnvironment= RANDOM_PORT)
class CzqDemoUserLoginApplicationTests {

	@Autowired
	public UserService userService;
	
	@Test
	void contextLoads() {
		UserCreateReqVo userCreateReqVo=new UserCreateReqVo();
		userCreateReqVo.setName("test2");
		userCreateReqVo.setPassword("qwe123");
		userService.createUser(userCreateReqVo);
		
		List<User> users=userService.selectUser("test2");
		User user=users.get(0);
		user.setName("test3");
		userService.updateUser(user);
		
		userService.deleteUser("test3");
	}

}
