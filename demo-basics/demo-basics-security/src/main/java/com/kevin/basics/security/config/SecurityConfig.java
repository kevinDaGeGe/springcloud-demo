package com.kevin.basics.security.config;

import com.kevin.basics.security.dao.PermissionMapper;
import com.kevin.basics.security.entity.Permission;
import com.kevin.basics.security.handler.MyAuthenticationFailureHandler;
import com.kevin.basics.security.handler.MyAuthenticationSuccessHandler;
import com.kevin.basics.security.service.MyUserDetailsService;
import com.kevin.basics.security.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyAuthenticationSuccessHandler successHandler;
	@Autowired
	private MyAuthenticationFailureHandler failHandler;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private PermissionMapper permissionMapper;

	
	// 用户认证信息
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置用户账号信息和权限
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("123456").
		 * authorities("showOrder","addOrder","updateOrder","deleteOrder"); // 添加
		 * useradd账号 只有添加查询和添加订单权限
		 * auth.inMemoryAuthentication().withUser("userAdd").password("123456")
		 * .authorities("showOrder","addOrder");
		 */

		// 如果想实现动态账号与数据库关联 在该地方改为查询数据库
		auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {

			// 加密的密码与数据库密码进行比对CharSequence rawPassword 表单字段 encodedPassword
			// 数据库加密字段
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				System.out.println("rawPassword:" + rawPassword + ",encodedPassword:" + encodedPassword);
				// 返回true 表示认证成功 返回fasle 认证失败
				return MD5Util.encode((String) rawPassword).equals(encodedPassword);
			}

			// 对表单密码进行加密
			public String encode(CharSequence rawPassword) {
				System.out.println("rawPassword:" + rawPassword);
				return MD5Util.encode((String) rawPassword);
			}
		});
	}
	
	// 配置拦截请求资源
		protected void configure(HttpSecurity http) throws Exception {
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
					.authorizeRequests();
			// 1.读取数据库权限列表
			List<Permission> listPermission = permissionMapper.findAllPermission();
			for (Permission permission : listPermission) {
				// 设置权限
				authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
			}
			authorizeRequests.antMatchers("/login").permitAll().antMatchers("/**").fullyAuthenticated().and().formLogin()
					.loginPage("/login").successHandler(successHandler).and().csrf().disable();

		}

	// OAuth2.0 授权中心管理器
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}

	// 用户认证信息
	protected void configure2(AuthenticationManagerBuilder auth) throws Exception {
		// 设置用户账号信息和权限
		
		 auth.inMemoryAuthentication().withUser("admin").password("123456").
		 authorities("showOrder","addOrder","updateOrder","deleteOrder"); 
		 
		 // 添加useradd账号 只有添加查询和添加订单权限
		 auth.inMemoryAuthentication().withUser("userAdd").password("123456")
		 .authorities("showOrder","addOrder");
		 
	}
	// 配置HttpSecurity 拦截资源
	protected void configure2(HttpSecurity http) throws Exception {
		// // 拦截请求, 权限名称
		http.authorizeRequests().antMatchers("/showOrder").hasAnyAuthority("showOrder").antMatchers("/addOrder")
				.hasAnyAuthority("addOrder").antMatchers("/login").permitAll().antMatchers("/updateOrder")
				.hasAnyAuthority("updateOrder").antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
				// 并且关闭csrf
				.antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
				.successHandler(successHandler).failureHandler(failHandler).and().csrf().disable();

	}

	

	// SpringBoot2.0抛弃了原来的NoOpPasswordEncoder，要求用户保存的密码必须要使用加密算法后存储，在登录验证的时候Security会将获得的密码在进行编码后再和数据库中加密后的密码进行对比
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
