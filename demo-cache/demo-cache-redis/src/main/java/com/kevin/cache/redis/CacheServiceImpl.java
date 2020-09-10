package com.kevin.cache.redis;

import com.kevin.cache.CacheService;
import com.kevin.util.SerializeUtil;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@Service
public class CacheServiceImpl implements CacheService {
    private static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
//    @Autowired
    private JedisPool jedisPool;

    /**获取jedis实例*/
    public Object getResource() throws Exception{
        return jedisPool.getResource();
    }

    /**设置key与value*/
    public void set(String key, String value,String nxxx,String expx,long time) {
        Jedis jedis=null;
        try{
            jedis = (Jedis) getResource();
            jedis.set(key,value,nxxx,expx,time);
        } catch (Exception e) {
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    /**根据key获取value*/
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = (Jedis) getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    /**判断是否存在key*/
    public boolean containKey(String key){
        boolean b;
        Jedis jedis = null;
        try{
            jedis = (Jedis) getResource();
            b = jedis.exists(key);
            return b;
        }catch (Exception e){
            logger.error("Redis server error:："+e.getMessage());
            return false;
        }finally {
            returnResource(jedis);
        }
    }

    /**释放jedis实例资源*/
    public void returnResource(Object o) {
        Jedis jedis = (Jedis)o;
        if(jedis != null){
            jedis.close();
        }
    }

    /**获取key*/
    public String getKeyForAop(JoinPoint joinPoint, HttpServletRequest request){
        //获取参数的序列化
        Object[] objects = joinPoint.getArgs();
        String args = SerializeUtil.serializeObject(objects[0]);
        //获取请求url
        String url = request.getRequestURI();
        //获取请求的方法
        String method = request.getMethod();
        //获取当前日期,规避默认init情况
        String date = LocalDate.now().toString();
        Thread.yield();
        //key值获取
        return args + url + method + date;
    }
}