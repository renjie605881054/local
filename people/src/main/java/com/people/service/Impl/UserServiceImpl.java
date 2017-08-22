package com.people.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.people.dao.UserDao;
import com.people.pojo.User;
import com.people.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public User getUserById(int userId) {
		return this.userDao.getUserById(userId);
	}
	
}
