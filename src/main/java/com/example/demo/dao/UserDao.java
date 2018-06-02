package com.example.demo.dao;

import com.example.demo.model.UserInfo;

public interface UserDao {

	UserInfo findUser(String username);

    UserInfo findUserById(Long userId);
}
