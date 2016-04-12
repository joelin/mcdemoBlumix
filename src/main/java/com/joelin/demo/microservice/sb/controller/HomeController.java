package com.joelin.demo.microservice.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/home/")
public class HomeController {
	
	@RequestMapping("adduser")
	public String addUser(){
		return "user/customer/user-add.jsp";
	}
}
