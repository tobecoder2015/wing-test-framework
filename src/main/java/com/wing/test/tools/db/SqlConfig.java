package com.wing.test.tools.db;

import lombok.Data;

/**
 * Created by Administrator on 2017/7/16.
 */
@Data
public class SqlConfig {
    private String name;
    private String driver;
    private String url;
    private String username;
    private String password;
    protected int maxActive = 8;
    protected int maxIdle = 8;
    protected int minIdle = 0;
    protected int initialSize = 0;
}
