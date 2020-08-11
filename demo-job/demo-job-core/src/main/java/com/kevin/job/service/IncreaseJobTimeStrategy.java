package com.kevin.job.service;
/**
 * Copyright © 2020 zlpay.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kevin.job.producer.SendMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Description:时间递增任务策略
 * 抽象出时间依次增加定时调用实现类
 * 时间增加类型比如,5秒,20秒,1分钟,5分钟,1小时
 * @author: yu.han
 * @date: 2020/8/4 16:15
 */
public abstract class IncreaseJobTimeStrategy {
    @Autowired
    private SendMessageInterface sendMessageInterface;

    protected abstract void doJob(String msg);
    protected abstract Long[] getJobTimes();

    protected void getConsumer(String msg){
        JSONObject jsonObject = JSON.parseObject(msg);
        Long job_next_interval_time = jsonObject.getLong("JOB_NEXT_INTERVAL_TIME");
        if(job_next_interval_time!=null&&job_next_interval_time!=0){
            //如果不到通知时间,重新放入队列
            if(System.currentTimeMillis()>job_next_interval_time){
                System.out.println("时间不到重新放入队列,msg:" + msg);
                Message build = MessageBuilder.withPayload(msg.getBytes()).build();
                sendMessageInterface.sendMsg().send(build);
            }
            //执行job实现
            try {
                doJob(msg);
            } catch (Exception e) {
                //任务失败重新放入队列,增加执行时间间隔
                int JOB_NEXT_TIMES = jsonObject.getInteger("JOB_NEXT_TIMES");
                Long[] jobTimes = getJobTimes();
                if(JOB_NEXT_TIMES>=jobTimes.length) {
                    //超出重复次数,退出队列
                    return;
                }
                ++JOB_NEXT_TIMES;
                jsonObject.put("JOB_NEXT_TIMES",JOB_NEXT_TIMES);
                jsonObject.put("JOB_NEXT_INTERVAL_TIME",jobTimes[JOB_NEXT_TIMES]);
                String sendMsg=jsonObject.toJSONString();
                System.out.println("任务失败重新放入队列,增加执行时间间隔,msg:" + sendMsg);
                Message build = MessageBuilder.withPayload(sendMsg.getBytes()).build();
                sendMessageInterface.sendMsg().send(build);
            }
        }
    }

}
