package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author   czq
 * @Date 2020-09-08 15:22:55    
 */
@Controller
@RequestMapping(value="/czq/hello")
public class HelloController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String indexData() {
		return "login";
	}
}
