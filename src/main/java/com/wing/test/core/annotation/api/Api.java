package com.wing.test.core.annotation.api;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Api {
    String name() default "";
    String singFields() default "";
    String path() default "";
    String db() default "";
    String developer() default "";
    String requirementOwner() default "";
    String module() default "";
    String group() default "";
    String description() default "";
    boolean enable() default true;
    ApiLevel level() default ApiLevel.NORMAL;


}
