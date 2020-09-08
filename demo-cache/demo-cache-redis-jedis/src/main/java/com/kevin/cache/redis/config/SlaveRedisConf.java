package com.kevin.cache.redis.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
slave备机，读取
*/
@Configuration
@EnableCaching
public class SlaveRedisConf extends RedisConfig {

    @Bean(name = "lbsJedisConnectionFactory")
    @Override
    public JedisConnectionFactory getRedisConnFactory(@Qualifier("lbsRedisProperties")RedisProperties redisProperties) {
        return super.getRedisConnFactory(redisProperties);
    }

    @Bean(name = "lbsRedisTemplate")
    @Override
    public RedisTemplate<Object, Object> buildRedisTemplate(@Qualifier("lbsJedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return super.buildRedisTemplate(redisConnectionFactory);
    }

    @Bean(name = "lbsRedisProperties")
    @ConfigurationProperties(prefix = "spring.redis.lbs")
    public RedisProperties getBaseDBProperties() {
        return new RedisProperties();
    }
}