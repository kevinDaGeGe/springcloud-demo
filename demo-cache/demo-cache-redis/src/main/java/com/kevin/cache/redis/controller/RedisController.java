package com.kevin.cache.redis.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevin.cache.redis.listener.LettuceKeyExpiredListener;
import com.kevin.cache.redis.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
	@org.springframework.beans.factory.annotation.Autowired
	private com.kevin.cache.redis.listener.RedisUtil redisUtil;
	@Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;
	@Autowired
	LettuceKeyExpiredListener keyExpiredListener= new LettuceKeyExpiredListener();
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value = "/psubscribe")
    public String psubscribe(String key) {
        log.info("psubscribe={}",key);
        redisUtil.psubscribe(keyExpiredListener, key);
        return "success";
	}
	@RequestMapping(value = "/setString")
    public String setString(String key) {
		String value = System.currentTimeMillis()+"";
		redisService.set(key, value);
        return "success";
	}
	@GetMapping(value = "/setList", produces = "application/json;charset=UTF-8")
    public String setList(int size) {
		String s = "listen_";
		for (int i = 0; i < size; i++) {
			setString(s+i);
		}
		log.info("setList end...");
        return "success";
	}
}