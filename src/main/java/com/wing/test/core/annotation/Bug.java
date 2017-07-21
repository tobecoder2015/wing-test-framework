package com.wing.test.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bug {
    String createBy() default "";
    String url() default "";
    String description() default "";
    String status() default "";

}
