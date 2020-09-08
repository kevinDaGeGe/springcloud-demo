package com.kevin.cache.redis.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
public class LettuceRedisConfig {

	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.database}")
	private int database;
//	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		// 通过配置RedisStandaloneConfiguration实例来
		// 创建Redis Standolone模式的客户端连接创建工厂
		// 配置hostname和port
//		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("kevin2", 6379));
		RedisStaticMasterReplicaConfiguration redisConfiguration = new RedisStaticMasterReplicaConfiguration(host, port);
//		RedisSocketConfiguration redisConfiguration = new RedisSocketConfiguration(host, port);
		redisConfiguration.setPassword(password);
		redisConfiguration.setDatabase(database);
//		redisConfiguration.addNode("kevin2", 16379);
//		redisConfiguration.addNode("kevin4", port);
		return new LettuceConnectionFactory(redisConfiguration);
	}

//	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}
}