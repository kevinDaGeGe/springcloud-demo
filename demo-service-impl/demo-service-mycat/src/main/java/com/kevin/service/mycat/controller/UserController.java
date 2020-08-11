package com.kevin.service.mycat.controller;

import com.kevin.service.api.mycat.BO.UserEntity;
import com.kevin.service.mycat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/findUser")
	public List<UserEntity> findUser() {
		return userServiceImpl.findUser();
	}

	@RequestMapping("/findUserByName")
	public UserEntity findUserByName(String userName) {
		return userServiceImpl.findUserByName(userName);
	}

	@RequestMapping("/insertUser")
	public List<UserEntity> insertUser(String userName) {
		return userServiceImpl.insertUser(userName);
	}

}
