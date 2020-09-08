package com.kevin.cache.redis.service.impl;

import java.util.List;

import org.redisson.client.RedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.kevin.cache.redis.service.RedisService;

/**
 * Simple to Introduction
 * className: RedisServiceImpl
 *
 * @author EricYang
 * @version 2018/8/4 23:00
 */
@Service
@Order(5)
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate template;


    public List<Object> setList(){
    	//从一个队列中删除指定数量的元素
    	List<Object> results = template.executePipelined(
    	  //RedisCallback内部类
    	  //实现doInRedis方法
    	  //此处亦可以创建SessionCallack的内部类
    	  //由用户自己决定
    	  new RedisCallback() {
    	    public Object doInRedis(RedisConnection connection) throws DataAccessException {
    	      //创建连接
    	      StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
    	      for(int i=0; i< 1000; i++) {
    	        //循环调用rpop进行batchSize次右删除
    	    	String value = System.currentTimeMillis()+"";
    	        stringRedisConn.sAdd("listen_"+i, value);
    	      }
    	    return null;
    	  }

			@Override
			public Object doInRedis(org.springframework.data.redis.connection.RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
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

    @Override
    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, value);
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


