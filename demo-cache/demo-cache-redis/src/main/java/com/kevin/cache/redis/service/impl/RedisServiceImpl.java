package com.kevin.cache.redis.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import com.kevin.cache.redis.service.RedisService;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import lombok.extern.slf4j.Slf4j;

/**
 * Simple to Introduction
 * className: RedisServiceImpl
 *
 * @author EricYang
 * @version 2018/8/4 23:00
 */
@Service
@Order(5)
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate template;

    @Override
    public List<Object> setList(int size){
    	//从一个队列中删除指定数量的元素
    	List<Object> results = template.executePipelined(
    	  //RedisCallback内部类
    	  //实现doInRedis方法
    	  //此处亦可以创建SessionCallack的内部类
    	  //由用户自己决定
    		new RedisCallback<Object>() {
    	    public Object doInRedis(RedisConnection connection) throws DataAccessException {
    	      //创建连接
    	      StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
    	      for(int i=0; i< size; i++) {
    	        //循环调用rpop进行batchSize次右删除
    	    	String value = System.currentTimeMillis()+"";
    	    	String key = "listen_"+i;
    	    	log.info("key="+key);
				stringRedisConn.set(key, value,Expiration.seconds(60),SetOption.SET_IF_ABSENT);
				stringRedisConn.closePipeline();
    	      }
    	    return null;
    	  }

    	});
    	return results;
    }
    @Override
    public String get(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
    public String getL(String key) {
    	
    	RedisClient client = RedisClient.create(""); 
    	StatefulRedisConnection<String, String> connection = client.connect(); 
//    	RedisCommands<String, String> commands = connection.sync();
//    	String value = commands.get("key"); 	
    	RedisAsyncCommands<String, String> commands = connection.async();
    	RedisFuture<String> future = commands.get("key"); 
    	String value = null;
		try {
			value = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    	return value;
    }
    

    @Override
    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, value,Duration.ofMinutes(1));
    }

    @Override
    public String getHash(String key, String hashKey) {
        HashOperations<String, String, String> hashOps = this.template.opsForHash();
        return hashOps.get(key, hashKey);
    }

    @Override
    public void setHash(String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOps = this.template.opsForHash();
        hashOps.put(key, hashKey, value);
    }
}


