package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.req.UserCreateReqVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author   czq
 * @Date 2020-09-08 15:38:13    
 */
@Api(description = "主页模块")
@Controller
@RequestMapping(value="/")
public class IndexController {

	@ApiOperation(value = "主页直接跳转登录页")
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView indexData(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
	
	@ApiOperation(value = "主页直接跳转登录页")
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView indexDataI(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
}