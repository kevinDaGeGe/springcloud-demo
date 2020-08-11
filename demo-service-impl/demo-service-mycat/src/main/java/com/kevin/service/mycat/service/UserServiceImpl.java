package com.kevin.service.mycat.service;

import com.kevin.service.api.mycat.BO.UserEntity;
import com.kevin.service.api.mycat.service.UserService;
import com.kevin.service.mycat.mapper.UserMapper;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
@EnableSwagger2Doc
@Api("订单服务")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
//	@Hystrix
	@GetMapping("findUserByName")
	@ApiOperation(value = "根据用户名查询用户信息", notes = "查询数据库中某个的用户信息")
	@ApiImplicitParam(name = "userName", value = "用户名", paramType = "path", required = true, dataType = "String")
	public UserEntity findUserByName(String userName){
		return userMapper.findUserByName(userName);
	}
	public List<UserEntity> findUser() {
		return userMapper.findUser();
	}

	public List<UserEntity> insertUser(String userName) {
		return userMapper.insertUser(userName);
	}

}
