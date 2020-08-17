package com.kevin.service.sharding.controller;

import com.kevin.service.api.sharding.BO.UserEntity;
import com.kevin.service.sharding.service.UserService;
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
@Api("shardingjdbc服务项目")
public class UserController {
	@Value("${server.port}")
	private String serverPort;
	@Autowired
	private UserService userService;

	@ApiOperation(value = "查询全部用户信息", notes = "查询全部用户信息Notes")
	@PostMapping("/findUser")
	public List<UserEntity> findUser() {
		return userService.findUser();
	}

	@ApiOperation(value = "保存用户信息", notes = "在数据库增加用户信息记录")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
	@PostMapping("/insertUser")
	public String insertUser(String userName) {
		return userService.insertUser(userName) > 0 ? "success" : "fail";
	}
	@GetMapping("/")
	public String index() {
		return "app-kevin-shardingjdbc:"+serverPort;
	}
}
