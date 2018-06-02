package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserInfo findUser(String username) {
		
		return userDao.findUser(username);
	}

	@Override
	public UserInfo findUserById(Long userId) {
		return userDao.findUserById(userId);
	}

}
