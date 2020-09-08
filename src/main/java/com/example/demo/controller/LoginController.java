package com.example.demo.controller;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author   czq
 * @Date 2020-09-08 15:38:13    
 */
@Controller
@RequestMapping(value="/czq/login")
public class LoginController {

	@RequestMapping(value="",method = RequestMethod.POST)
	public ModelAndView indexData(ModelAndView modelAndView,@RequestParam("name") String name,@RequestParam("password") String password) {
		System.out.println("校验通过： name:"+name+" 密码 ："+password);
		modelAndView.addObject("czq", "加油");
		modelAndView.setViewName("index");
        return modelAndView;
	}
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView indexData(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
}