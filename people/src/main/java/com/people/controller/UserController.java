package com.people.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.people.pojo.User;
import com.people.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("showUser")
	public String showUser(){
		User user = userService.getUserById(1);
		System.out.println(user);
		return "user";
	}
}
