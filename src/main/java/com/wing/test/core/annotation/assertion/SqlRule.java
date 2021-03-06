package com.wing.test.core.annotation.assertion;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlRule {
String sqlConnName() default "";
    Op op() default Op.eq;
    String sql();
    String filed();
    Class<? extends Comparable> type() default String.class;
    String expectedVal() default "";
}
