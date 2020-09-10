package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.other.ResultHelper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.req.UserCreateReqVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author   czq
 * @Date 2020-09-08 10:28:13    
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST) 
	public ResponseEntity<Map<String, ?>> createUser(@RequestBody UserCreateReqVo userCreateReqVo){
		userService.createUser(userCreateReqVo);
		return ResultHelper.success();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST) 
	@ResponseBody
	public Boolean addUser(@RequestBody UserCreateReqVo userCreateReqVo){
		userService.createUser(userCreateReqVo);
		return true;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET) 
	public String addUser(){
		return "user/userAdd";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Map<String, ?>> selectUser(@RequestParam("name") String name){
		List<User> users=userService.selectUser(name);
		return ResultHelper.successItemList(users);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean updateUser(@RequestBody User user){
		userService.updateUser(user);
		return true;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, ?>> deleteUser(@RequestParam("name") String name){
		userService.deleteUser(name);
		return ResultHelper.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, ?>> deleteUserById(@PathVariable   Integer id){
		userService.deleteUserById(id);
		return ResultHelper.success();
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String toUserIndex(){
		return "user/userIndex";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toUserList(@RequestParam(name = "page", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String toUserEdit(@PathVariable   Integer id, Model model) {
        User user = userService.selectUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }
}
