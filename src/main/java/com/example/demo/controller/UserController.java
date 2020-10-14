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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author   czq
 * @Date 2020-09-08 10:28:13    
 */
@Api(description = "用户管理模块")
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;
	
	@ApiOperation(value = "创建用户")
	@RequestMapping(value = "/", method = RequestMethod.POST) 
	public ResponseEntity<Map<String, ?>> createUser(@RequestBody UserCreateReqVo userCreateReqVo){
		userService.createUser(userCreateReqVo);
		return ResultHelper.success();
	}
	
	@ApiOperation(value = "创建用户")
	@RequestMapping(value = "/add", method = RequestMethod.POST) 
	@ResponseBody
	public Boolean addUser(@RequestBody UserCreateReqVo userCreateReqVo){
		userService.createUser(userCreateReqVo);
		return true;
	}
	
	@ApiOperation(value = "跳转创建用户页面")
	@RequestMapping(value = "/add", method = RequestMethod.GET) 
	public String addUser(){
		return "user/userAdd";
	}
	
	@ApiOperation(value = "选择用户")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Map<String, ?>> selectUser(@ApiParam(name = "name", value = "用户名", required = true) @RequestParam("name") String name){
		List<User> users=userService.selectUser(name);
		return ResultHelper.successItemList(users);
	}
	
	@ApiOperation(value = "编辑用户")
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public Boolean updateUser(@RequestBody User user){
		userService.updateUser(user);
		return true;
	}
	
	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Map<String, ?>> deleteUser(@ApiParam(name = "name", value = "用户名", required = true) @RequestParam("name") String name){
		userService.deleteUser(name);
		return ResultHelper.success();
	}
	
	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Map<String, ?>> deleteUserById(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable   Integer id){
		userService.deleteUserById(id);
		System.out.println(ResultHelper.successItemSingle(true));
		return ResultHelper.successItemSingle(true);
	}
	
	@ApiOperation(value = "跳转用户主页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String toUserIndex(){
		return "user/userIndex";
	}
	
	@ApiOperation(value = "用户列表展示接口")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toUserList(@ApiParam(name = "pageNum", value = "第几页", required = true) @RequestParam(name = "page", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<User> userList = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }
	
	@ApiOperation(value = "跳转用户编辑界面")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String toUserEdit(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable   Integer id, Model model) {
        User user = userService.selectUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }
}
