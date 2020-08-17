package com.kevin.common.thread.log;
/**
 * Copyright © 2020 zlpay.
 */

import com.kevin.common.thread.job.PutQueue;
import org.springframework.scheduling.annotation.Async;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/13 15:25
 */
public class LogUtil {

    private static int log_level_LOCAL;
    private static int log_level_COLLECTION;
    private Class clazz;

    public LogUtil(Class clazz) {
        this.clazz = clazz;
    }
    @Async
    protected void log(int logLevel,String msg,Object...o) {
        if (log_level_LOCAL <= logLevel) {
            System.out.println(msg);
        }
        if (log_level_LOCAL <= logLevel) {
            PutQueue.putqueue("LOG_QUEUE", msg + o.toString());
        }
    }
    public void info(String msg,Object...o){
        log(2,msg,o);
    }
    public void debug(String msg,Object...o){
        log(1,msg,o);
    }
}
