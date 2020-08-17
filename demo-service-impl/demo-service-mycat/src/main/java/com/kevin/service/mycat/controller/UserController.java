package com.kevin.service.mycat.controller;

import com.kevin.service.api.mycat.BO.UserEntity;
import com.kevin.service.mycat.service.UserServiceImpl;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableSwagger2Doc
@Api("mycat服务项目")
public class UserController {
	@Value("${server.port}")
	private String serverPort;
	private int id;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@ApiOperation(value = "查询全部用户信息", notes = "查询全部用户信息Notes")
	@PostMapping("/findUser")
	public List<UserEntity> findUser() {
		return userServiceImpl.findUser();
	}

	@ApiOperation(value = "根据用户名查询用户信息", notes = "查询数据库中某个的用户信息")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
	@PostMapping("/findUserByName")
	public UserEntity findUserByName(String userName) {
		return userServiceImpl.findUserByName(userName);
	}
	@ApiOperation(value = "保存用户信息", notes = "在数据库增加用户信息记录")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
	@PostMapping("/insertUser")
	public List<UserEntity> insertUser(String userName) {
		return userServiceImpl.insertUser(++id,userName);
	}
	@GetMapping("/")
	public String index() {
		return "app-kevin-mycat:"+serverPort;
	}

}
