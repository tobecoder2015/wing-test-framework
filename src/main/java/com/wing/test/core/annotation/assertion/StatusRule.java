package com.wing.test.core.annotation.assertion;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusRule {
    int code() default 200;
    String error() default "状态码校验失败";
}
