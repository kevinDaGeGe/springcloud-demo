package com.kevin.cache.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.kevin.cache.redis.service.RedisService;

@Service
@lombok.extern.slf4j.Slf4j
public class LettuceKeyExpiredListener implements MessageListener {
	@Autowired
	private RedisService redisService;
	@Override
	public void onMessage(Message message, byte[] pattern) {
		 byte[] bytesBody = message.getBody();
	        byte[] bytesChannel = message.getChannel();
	        String patternStr = pattern != null? new String(bytesChannel): null;
	        //msgBody=qq, channel=__keyevent@1__:expired
	        String bodyChannel = bytesChannel!=null? new String(bytesChannel): null;
	        if(!bodyChannel.contains("expired")) {
	        	long endTime = System.currentTimeMillis();
	        	String subPattern = patternStr.substring(patternStr.lastIndexOf(":")+1);
	        	String msg = redisService.get(subPattern);
	        	long startTime = Long.parseLong(msg);
				log.info("patternStr:"+subPattern+",耗时:"+(endTime-startTime));
	        }else {
	        	
	        }
	        
			log.info("msgBody={}, channel={}, pattern={}",  bytesBody!=null? new String(bytesBody): null, bodyChannel, patternStr);

	}
}