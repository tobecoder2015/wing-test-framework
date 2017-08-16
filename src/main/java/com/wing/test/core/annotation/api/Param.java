package com.wing.test.core.annotation.api;

import java.lang.annotation.*;


/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String name();
    ParamType type() default ParamType.QUERY;
}
