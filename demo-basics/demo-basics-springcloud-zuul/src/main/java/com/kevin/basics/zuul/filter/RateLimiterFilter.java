/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.kevin.basics.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimiterFilter extends ZuulFilter {

	//漏桶算法,0.5代表一秒最多多少个
	RateLimiter rateLimiter = RateLimiter.create(0.5);
	//令牌桶算法
	RateLimiter rateLimiter2 = RateLimiter.create(0.5,10, TimeUnit.SECONDS);
	// 编写过滤器拦截业务逻辑代码
	public Object run() throws ZuulException {
		//等待时间
		double time = rateLimiter.acquire();
		System.out.println("等待时间:" + time);
		return null;
	}

	public boolean shouldFilter() {

		return true;
	}

	// 过滤器执行顺序,当一个请求在同一个阶段的时候存在多个过滤器的时候，多个过滤器执行顺序
	public int filterOrder() {
		return 0;
	}

	// 过滤类型 pre 表示在请求之前进行执行
	@Override
	public String filterType() {
		return "pre";
	}

	// 网关过滤器如何编写

}
