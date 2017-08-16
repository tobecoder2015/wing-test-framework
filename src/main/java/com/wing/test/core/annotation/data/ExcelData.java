package com.wing.test.core.annotation.data;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/16.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelData {

    String file();
    String sheet();
    int row();
    int col();
}
