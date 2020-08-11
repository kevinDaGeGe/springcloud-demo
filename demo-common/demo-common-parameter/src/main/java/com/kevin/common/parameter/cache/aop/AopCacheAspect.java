package com.kevin.common.parameter.cache.aop;
/**
 * Copyright © 2020 zlpay.
 */

import com.alibaba.fastjson.JSONObject;
import com.kevin.cache.CacheService;
import com.kevin.cache.ehcache.EhCacheUtils;
import com.kevin.common.parameter.cache.enums.UseCache;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private RedisService redisService;
    @Autowired
    CacheService cacheService;

    /**
     * 设置切入点
     */
    //方法上面有@NeedCacheAop的方法,增加灵活性
    @Pointcut("@annotation(com.kevin.common.parameter.cache.enums.UseCache)")
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

        //获取注解对应配置过期时间
        UseCache cacheAop = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(UseCache.class);  //获取注解自身
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
        // 1.先查询一级缓存 key 以 当前的类名+方法名称+id +参数值FD
        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + id;
        // 1.1 查询一级缓存数据有对应的值存在，如果存在直接返回
        Users users = (Users) ehCacheUtils.get(cacheName, key);
        if (users != null) {
            System.out.println("key:" + key + ",从一级缓存获取数据:users:" + users.toString());
            return users;
        }
        // 1.1 查询一级缓存数据没有对应的值存在，直接查询二级缓存redis redis 中如何存放对象呢？ json格式
        // 2.查询二级缓存
        String userJSON = redisService.getString(key);
        // 如果redis缓存中有这个对应的 值，修改一级缓存
        if (!StringUtils.isEmpty(userJSON)) {
            JSONObject jsonObject = new JSONObject();
            Users resultUser = jsonObject.parseObject(userJSON, Users.class);
            // 存放在一级缓存
            ehCacheUtils.put(cacheName, key, resultUser);
            return resultUser;
        }
        // 3.查询db 数据库
        Users user = userMapper.getUser(id);
        if (user == null) {
            return null;
        }
        // 如何保证 两级缓存有效期相同 时差问题
        // 存放二级缓存redis
        redisService.setString(key, new JSONObject().toJSONString(user));
        // 存放在一级缓存

        ehCacheUtils.put(cacheName, key, user);
        // 一级缓存的有效期时间减去二级缓存执行代码时间


    }


}
