package com.kevin.common.thread.job;
/**
 * Copyright © 2020 zlpay.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/13 15:15
 */
public class PutQueue {
    private static ArrayBlockingQueue<JSONObject> queue = new ArrayBlockingQueue<JSONObject>(200);
    public static void putqueue(String queueName,Object o){
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("queueName",queueName);
        jsonObject.put("object",o);
        try {
            queue.put(jsonObject);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
