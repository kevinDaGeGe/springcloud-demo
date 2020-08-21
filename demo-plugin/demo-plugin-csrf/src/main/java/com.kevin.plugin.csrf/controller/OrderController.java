package com.kevin.plugin.csrf.controller;

import com.kevin.plugin.csrf.entity.OrderEntity;
import com.kevin.plugin.csrf.mapper.OrderMapper;
import com.kevin.plugin.csrf.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class OrderController {

	@Autowired
	private OrderMapper orderMapper;

	@RequestMapping("/getToken")
	public String getToken() {
		return TokenUtils.getToken();
	}

	// 验证Token
	@RequestMapping(value = "/addOrder", produces = "application/json; charset=utf-8")
	public String addOrder(@RequestBody OrderEntity orderEntity, HttpServletRequest request) {
		// 代码步骤：
		// 1.获取令牌 存放在请求头中
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			return "参数错误!";
		}
		// 2.判断令牌是否在缓存中有对应的令牌
		// 3.如何缓存没有该令牌的话，直接报错（请勿重复提交）
		// 4.如何缓存有该令牌的话，直接执行该业务逻辑
		// 5.执行完业务逻辑之后，直接删除该令牌。
		if (!TokenUtils.findToken(token)) {
			return "请勿重复提交!";
		}
		int result = orderMapper.addOrder(orderEntity);
		return result > 0 ? "添加成功" : "添加失败" + "";
	}

}
