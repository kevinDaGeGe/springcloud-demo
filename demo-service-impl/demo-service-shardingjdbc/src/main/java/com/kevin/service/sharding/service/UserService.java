package com.kevin.service.sharding.service;

import com.kevin.service.api.sharding.BO.UserEntity;
import com.kevin.service.sharding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	// 使用读的数据源
	public List<UserEntity> findUser() {
		return userMapper.findUser();
	}

	// 使用写的数据源
	public int insertUser(String userName) {
		return userMapper.insertUser(userName);
	}

}
