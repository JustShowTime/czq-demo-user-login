package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserService;
import com.example.demo.vo.req.UserCreateReqVo;

/**
 * @author   czq
 * @Date 2020-09-08 15:38:13    
 */
@Controller
@RequestMapping(value="/regist")
public class RegistController {

	@Autowired
	public UserService userService;
	
	@RequestMapping(value="",method = RequestMethod.POST)
	@ResponseBody
	public Boolean indexData(@RequestParam("name") String name,@RequestParam("password") String password) {
		System.out.println("校验通过： name:"+name+" 密码 ："+password);
		UserCreateReqVo userCreateReqVo=new UserCreateReqVo();
		userCreateReqVo.setName(name);
		userCreateReqVo.setPassword(password);
		userService.createUser(userCreateReqVo);
		return true;
	}
	

	
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView indexData(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("regist");
        return modelAndView;
	}
}