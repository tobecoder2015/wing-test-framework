package com.wing.test.core.annotation.assertion;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonRule {
String jsonPath() default "";
    Op op() default Op.eq;
    String expectedVal();
    Class<? extends Comparable> type() default String.class;
    String error() default "";
    boolean enabled() default true;
    String description() default "";
}
