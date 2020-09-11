package com.kevin.cache.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.lettuce.core.ReadFrom;

//@Configuration
class WriteToMasterReadFromReplicaConfiguration {

//配置Lettuce连接工厂
//  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
      .readFrom(ReadFrom.SLAVE_PREFERRED) //配置从读
      .build();
    //配置hostname和port
    RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("kevin2", 6379);
    //通过配置创建Lettuce连接工厂
    return new LettuceConnectionFactory(serverConfig, clientConfig);
  }
}