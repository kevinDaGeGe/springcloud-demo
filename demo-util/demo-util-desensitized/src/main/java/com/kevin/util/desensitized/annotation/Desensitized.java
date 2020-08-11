package com.kevin.util.desensitized.annotation;

import com.kevin.util.desensitized.enums.SensitiveTypeEnum;

import java.lang.annotation.*;


/**
 * 
 * @Description: 敏感信息注解标记
 * @author: YULONG.DU
 * @date: 2018年11月13日 下午5:05:01
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {

    /*脱敏类型(规则)*/
    SensitiveTypeEnum type();

    /*判断注解是否生效的方法*/
    String isEffictiveMethod() default "";

}
