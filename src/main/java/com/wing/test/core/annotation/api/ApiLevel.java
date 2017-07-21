package com.wing.test.core.annotation.api;

/**
 * Created by Administrator on 2017/7/16.
 */
public enum ApiLevel {
    HIGH(0),
    NORMAL(1),
    LOW(2);
    int value;
    ApiLevel(int value){
        this.value=value;
    }
}
