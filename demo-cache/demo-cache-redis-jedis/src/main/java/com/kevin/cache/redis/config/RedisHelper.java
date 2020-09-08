package com.kevin.cache.redis.config;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 获取redisTemplate
 */
@Component
public class RedisHelper {
   private Object dataObj;

    public Object getDataObj() {
        return dataObj;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate redisTemWriter;

    @Resource(name = "lbsRedisTemplate")
    private RedisTemplate redisTemReader;

    public synchronized void saveDataToRedis(String key, Object dataObj){
        try {
            redisTemWriter.opsForValue().set(key, dataObj);//调用Redistemplate api即可
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}