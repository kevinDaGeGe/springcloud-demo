package com.kevin.basics.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// 用户认证成功
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		System.out.println("用户登陆成功");
		res.sendRedirect("/");
	}

}
