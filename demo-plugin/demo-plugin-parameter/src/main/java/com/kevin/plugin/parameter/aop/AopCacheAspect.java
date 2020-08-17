package com.kevin.plugin.parameter.aop;
/**
 * Copyright © 2020 zlpay.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.kevin.cache.CacheService;
import com.kevin.cache.ehcache.EhCacheUtils;
import com.kevin.plugin.parameter.enums.UseCache;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description:拦截数据查询请求,按照缓存配置从redis或ehcace里面查询
 * @author: yu.han
 * @date: 2020/8/3 13:49
 */
@Aspect
@Component
public class AopCacheAspect {

    @Autowired
    private EhCacheUtils ehCacheUtils;
//    @Autowired
//    private RedisService redisService;
    @Autowired
    CacheService cacheService;

    RateLimiter rateLimiter = RateLimiter.create(5,1, TimeUnit.SECONDS);


    /**
     * 设置切入点
     */
    //方法上面有@NeedCacheAop的方法,增加灵活性
    @Pointcut("@annotation(com.kevin.plugin.parameter.enums.UseCache)")
    public void annotationAspect(){}

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(* com.kevin.*.api.service.impl.mapper.*.*(..))")
    public void controllerAspect(){}

    @Around(value = "controllerAspect()||annotationAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        //存储接口返回值
        Object object = new Object();

        MethodSignature methodSignature = (MethodSignature) ((MethodSignature)joinPoint.getSignature());
        // 被切的方法
        Method method = methodSignature.getMethod();
        // 返回类型
        Class<?> methodReturnType = method.getReturnType();
        // 实例化
//        Object o = methodReturnType.newInstance();

        //获取注解对应配置过期时间
        UseCache cacheAop = method.getAnnotation(UseCache.class);  //获取注解自身
        String nxxx;String expx;long time;
        if (cacheAop == null){//规避使用第二种切点进行缓存操作的情况
            nxxx = "nx";
            expx = "ex";
            time = 30*60;  //默认过期时间为30分钟
        }else{
            nxxx = cacheAop.nxxx();
            expx = cacheAop.expx();
            time = cacheAop.time();
        }
        Object[] args = joinPoint.getArgs();
        String cacheName = joinPoint.getClass().getSimpleName();
        System.out.println(joinPoint);
        // 1.先查询一级缓存 key 以 当前的类名+方法名称+id +参数值FD
        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + args[0];
        // 1.1 查询一级缓存数据有对应的值存在，如果存在直接返回
        Object users = ehCacheUtils.get(cacheName, key);
        if (users != null) {
            System.out.println("key:" + key + ",从一级缓存获取数据:users:" + users.toString());
            return users;
        }
        // 1.1 查询一级缓存数据没有对应的值存在，直接查询二级缓存redis redis 中如何存放对象呢？ json格式
        // 2.查询二级缓存
        String userJSON = cacheService.get(key);
        // 如果redis缓存中有这个对应的 值，修改一级缓存
        if (StringUtils.isNotEmpty(userJSON)) {
            JSONObject jsonObject = new JSONObject();
            Object resultUser = jsonObject.parseObject(userJSON, methodReturnType.getClass());
            // 存放在一级缓存
            ehCacheUtils.put(cacheName, key, resultUser);
            return resultUser;
        }
        // 3.查询db 数据库
        Object resultUser= null;
        try {
            resultUser = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
        // 如何保证 两级缓存有效期相同 时差问题
        // 存放二级缓存redis
        cacheService.set(key, new JSONObject().toJSONString(resultUser),nxxx,expx,time);
        // 存放在一级缓存

        ehCacheUtils.put(cacheName, key, resultUser);
        // 一级缓存的有效期时间减去二级缓存执行代码时间


        notifyMQ("");
        return resultUser;
    }


    /**
     * 发送缓存使用信息到mq
     * @param msg 信息格式:className+methodName+id+useCacheOrDB+isDBData
     */
    @Async
    protected void notifyMQ(String msg){
        if(rateLimiter.tryAcquire()){
            //TODO...
        }
    }


}
