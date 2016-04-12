package com.joelin.demo.microservice.sb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(value = "/hello", produces = "text/plain")
	public String hello(@RequestParam("name") String name) {
		return "hello " + name;
	}

}
