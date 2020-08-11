package com.kevin.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举值校验注解
 *
 * @author: zetting
 * @date:2018/12/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumValidatorClass.class)
public @interface EnumValidator {
    Class<?> value();

    String message() default "枚举值不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}