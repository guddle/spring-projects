package com.dcits.demo.activiti.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldCtrl {

	@RequestMapping("/index")
	public String hello(Map<String, String> user){
		user.put("user", "guddle");
		return "index";
	}
}
