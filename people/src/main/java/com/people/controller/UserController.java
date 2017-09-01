package com.people.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public String showUser(HttpServletRequest request){
		User user = userService.getUserById(2);
		System.out.println(user);
		request.setAttribute("user", user.toString());
		return "user";
	}
}
