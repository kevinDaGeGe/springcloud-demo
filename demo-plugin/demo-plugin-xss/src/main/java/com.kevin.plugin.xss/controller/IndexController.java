
package com.kevin.plugin.xss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	// 转发到index页面
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	// 接受頁面 參數
	@RequestMapping("/postIndex")
	public String postIndex(HttpServletRequest request) {

		request.setAttribute("name", request.getParameter("name"));
		return "forward";
	}

}
