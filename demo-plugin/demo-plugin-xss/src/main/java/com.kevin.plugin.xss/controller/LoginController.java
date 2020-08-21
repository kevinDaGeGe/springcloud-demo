/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.kevin.plugin.xss.controller;

import com.kevin.plugin.xss.entity.UserEntity;
import com.kevin.plugin.xss.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/login")
	public String login(UserEntity userEntity) {
		System.out.println("账号密码信息:userEntity:" + userEntity.toString());
		UserEntity login = userMapper.login(userEntity);
		return login == null ? "登陆失败!" : "登陆成功!";
	}

}
