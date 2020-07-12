package com.wing.test.core.annotation.assertion;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HeaderRule {
String header();
    String value() default "";
    Op op() default Op.eq;
    String error() default "";
}
