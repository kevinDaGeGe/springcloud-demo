package com.kevin.common.parameter.cache.enums;

import java.lang.annotation.*;

/**
 * @Description:自定义注解，用于标识方法是否需要使用缓存
 * @author: yu.han
 * @date: 2020/8/3 10:55
 */
@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCache {
    public CacheLevel cacheLevel();
    public String cacheType();
    /**代表缓存策咯，nx:代表key不存在再进行缓存kv，xx:代表key存在再进行缓存kv  默认为"不存在key缓存key"*/
    String nxxx() default "nx";
    //代表过期时间单位，ex:秒 px:毫秒    默认为"秒"
    String expx() default "ex";
    //过期时间
    long time() default 30*60;
}
