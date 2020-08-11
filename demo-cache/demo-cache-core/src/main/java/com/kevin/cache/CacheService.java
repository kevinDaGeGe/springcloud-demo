package com.kevin.cache;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

public interface CacheService {
    /**获取jedis实例*/
    Object getResource() throws Exception;
    /**设置key与value*/
    void set(String key, String value, String nxxx, String expx, long time);
    /**根据key获取value*/
    String get(String key);
    /**判断是否存在key*/
    boolean containKey(String key);
    /**释放jedis实例资源*/
    void returnResource(Object jedis);
    /**获取key*/
    String getKeyForAop(JoinPoint joinPoint, HttpServletRequest request);
}