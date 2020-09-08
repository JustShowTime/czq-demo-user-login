package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.other.ResultHelper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.req.UserCreateReqVo;

/**
 * @author   czq
 * @Date 2020-09-08 10:28:13    
 */
@Controller
@RequestMapping("/czq/rest/user")
public class UserController {

	@Autowired
	public UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST) 
	public ResponseEntity<Map<String, ?>> createUser(@RequestBody UserCreateReqVo userCreateReqVo){
		userService.createUser(userCreateReqVo);
		return ResultHelper.success();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Map<String, ?>> selectUser(@RequestParam("name") String name){
		List<User> users=userService.selectUser(name);
		return ResultHelper.successItemList(users);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, ?>> updateUser(@RequestBody User user){
		userService.updateUser(user);
		return ResultHelper.success();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, ?>> deleteUser(@RequestParam("name") String name){
		userService.deleteUser(name);
		return ResultHelper.success();
	}
}
