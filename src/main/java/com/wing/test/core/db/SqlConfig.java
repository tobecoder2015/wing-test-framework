package com.wing.test.core.db;

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

}
