package com.kevin.job.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

// 创建管道接口
public interface SendMessageInterface {

	// 创建一个输出管道，用于发送消息
	@Output("springcloud-stream-rabbitMQ-test")
	SubscribableChannel sendMsg();

}
