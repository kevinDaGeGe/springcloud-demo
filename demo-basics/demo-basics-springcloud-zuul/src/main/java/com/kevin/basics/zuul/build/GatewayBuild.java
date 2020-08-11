package com.kevin.basics.zuul.build;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
* @Description: 
* @throws  
* @author: yu.han
* @date: 2020/8/5 17:13
*/
public interface GatewayBuild {

	/**
	 * 黑名单拦截
	 */
	Boolean blackBlock(RequestContext ctx, String ipAddres, HttpServletResponse response);

	/**
	 * 参数验证
	 */
	Boolean toVerifyMap(RequestContext ctx, String ipAddres, HttpServletRequest request);

}
