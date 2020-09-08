package com.kevin.cache.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;

import io.lettuce.core.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Subscriber {

	@Autowired
    private JedisPool jedisPool;
	
	public static void main(String[] args) {
		/*
		 * Jedis jedis = RedisClient.jedisPool.getResource(); jedis.psubscribe(new
		 * KeyExpiredListener(), "__key*__:*");
		 */
	}
}
