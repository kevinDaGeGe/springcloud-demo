package com.kevin.cache.redis.listener;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPubSub;

//@Component
public class KeyExpiredListener extends JedisPubSub {
	private KeyExpiredListener keyExpiredListener;
	@org.springframework.beans.factory.annotation.Autowired
	private RedisUtil redisUtil;
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }
 
    @Override
    public void onPMessage(String pattern, String channel, String message) {
    System.out.println(
        "pattern = [" + pattern + "], channel = [" + channel + "], message = [" + message + "]");
    //收到消息 key的键值，处理过期提醒
    }
 
    @PostConstruct
    public void init() {
        //不新建线程,会引起web项目启动阻塞
    	new Thread(){
    		public void run(){
//    			redisUtil.psubscribe(keyExpiredListener,"__keyevent@15__:expired");
    		}
    	}.start();
    	
    }
}