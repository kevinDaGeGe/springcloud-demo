package com.kevin.common.thread.log;
/**
 * Copyright © 2020 zlpay.
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @author: yu.han
 * @date: 2020/8/13 16:29
 */
public class LogFactory {
    private static Map<String,LogUtil> map =new ConcurrentHashMap<>();
    public static LogUtil getLog(Class<?> clazz){

        String className =clazz.getName();
        if(map.containsKey(className)){
            return map.get(clazz.getName());
        }
        LogUtil log = new LogUtil(clazz);
        map.put(className,log);
        return log;
    }
}
