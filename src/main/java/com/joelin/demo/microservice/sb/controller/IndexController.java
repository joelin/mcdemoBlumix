package com.joelin.demo.microservice.sb.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joelin.demo.microservice.sb.CurrentUserUtils;
import com.joelin.demo.microservice.sb.entity.UserEntity;
import com.joelin.demo.microservice.sb.service.LoginService;
import com.joelin.demo.microservice.sb.service.exception.ServiceException;


@Controller
public class IndexController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value={"login","/"},method=RequestMethod.GET)
	public String login(){
		return "login.jsp";
	}
	

	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(UserEntity user, RedirectAttributes redirect){
		try {
			user = loginService.login(user);
		} catch (ServiceException e) {
			logger.debug(e.getMessage());
			redirect.addFlashAttribute("err_code", e.getMessage());
			redirect.addFlashAttribute("user", user);
			return "redirect:/login";
		}
		
		CurrentUserUtils.getInstance().setUser(user);
		return "redirect:user/home";
	}

	@RequestMapping("user/home")
	public String home(){
		return "user/home.jsp";
	}
}
