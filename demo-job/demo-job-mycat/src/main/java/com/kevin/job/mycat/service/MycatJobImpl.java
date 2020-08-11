package com.kevin.job.mycat.service;
/**
 * Copyright © 2020 zlpay.
 */

import com.kevin.job.service.IncreaseJobTimeStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/4 17:10
 */
@Component
public class MycatJobImpl extends IncreaseJobTimeStrategy {

    @Value("${server.port}")
    private String serverPort;

    @Override
    protected void doJob(String msg) {
        System.out.println("mycat队列实际执行任务依次");
    }

    @Override
    protected Long[] getJobTimes() {
        return new Long[]{5000l,30000l,60000l,600000l};
    }
    @StreamListener("springcloud-stream-kafka-test")
    public void listener(String msg) {
        System.out.println("消费者获取生产消息:" + msg + ",端口号:" + serverPort);
        getConsumer(msg);
    }
}
