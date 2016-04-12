package com.joelin.demo.microservice.sb.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.joelin.demo.microservice.sb.CurrentUserUtils;
import com.joelin.demo.microservice.sb.entity.UserEntity;
import com.joelin.demo.microservice.sb.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	

	@ResponseBody
	@RequestMapping("list")
	public String findAll(){
		return JSON.toJSONString(userService.findAll());
	}
	

	@RequestMapping("post")
	public String post(UserEntity user){
		String curUser = CurrentUserUtils.getInstance().getUserId();
		user.setCreateById(Long.valueOf(curUser));
		user.setCreateDate(new Date());
		
		userService.save(user);
		return "redirect:/user/home/adduser";
	}
}
