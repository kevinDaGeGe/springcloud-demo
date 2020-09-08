package com.kevin.cache.redis.listener;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@lombok.extern.slf4j.Slf4j
public class RedisUtil {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private StringRedisTemplate template;
	@Autowired
	private LettuceKeyExpiredListener lettuceKeyExpiredListener;
	
	public void psubscribe(LettuceKeyExpiredListener keyExpiredListener,String pattern) {
		try {
			String parameter = "notify-keyspace-events"; 
			RedisConnection redisConnection = template.getConnectionFactory().getConnection();
//			redisConnection.setConfig(parameter, "E$xe");
			redisConnection.setConfig(parameter, "KEA");
			redisConnection.pSubscribe(keyExpiredListener, ("__keyspace@0__:"+pattern).getBytes("UTF-8") );
			log.info("订阅成功,key="+pattern);
		} catch (Exception e) {
			log.error("redis订阅异常", e);
		} finally {
		}
    }
//	 @PostConstruct
    public void init() {
        //不新建线程,会引起web项目启动阻塞
    	new Thread(){
    		public void run(){
	    			String listenPattern = "listen_*";
					redisUtil.psubscribe(lettuceKeyExpiredListener,listenPattern);
    		}
    	}.start();
    	
    }
}
