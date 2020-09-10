package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.vo.req.UserCreateReqVo;


/**
 * @author   czq
 * @Date 2020-09-08 15:38:13    
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {


	@RequestMapping(value="",method = RequestMethod.POST)
	@ResponseBody
	public Boolean indexData(@RequestParam("name") String name,@RequestParam("password") String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject subject = SecurityUtils.getSubject();
 
        subject.login(token);
        //login认证通过后，便可拿到 shiro 保存的用户对象
        User user1 = (User) subject.getPrincipal();
        subject.getSession().setAttribute("loginUser", user1);
		return true;
	}
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView indexData(ModelAndView modelAndView) {
		System.out.println("跳转页面成功");
		modelAndView.setViewName("login");
        return modelAndView;
	}
}