package com.kevin.cache.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.ReadFrom;

@Configuration
//@EnableCaching
@ComponentScan("com.kevin.cache.redis")
public class RedisConfig {
	@Bean
    public LettuceConnectionFactory redisConnectionFactory(@Qualifier("redisProperties")RedisProperties redisProperties) {
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("192.168.80.130", 6379));
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                // 哨兵地址
                .sentinel(redisProperties.getHost(), redisProperties.getPort());
//        sentinelConfig.setPassword(redisProperties.getPassword());
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().
        readFrom(ReadFrom.SLAVE_PREFERRED).build();
        return new LettuceConnectionFactory(sentinelConfig, clientConfig);
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 可以配置对象的转换规则，比如使用json格式对object进行存储。
        // Object --> 序列化 --> 二进制流 --> redis-server存储
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
    @Bean(name = "redisProperties")
    @ConfigurationProperties(prefix = "spring.redis.sentinel")
    public RedisProperties getBaseDBProperties() {
        return new RedisProperties();
    }

}