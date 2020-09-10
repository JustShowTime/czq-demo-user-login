package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.req.UserCreateReqVo;


/**
 * @author   czq
 * @Date 2020-09-08 15:38:13    
 */
@Controller
@RequestMapping(value="/")
public class IndexController {

	
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView indexData(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
	
	@RequestMapping(value="index",method = RequestMethod.GET)
	public ModelAndView indexDataI(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
}