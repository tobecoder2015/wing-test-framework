package com.wing.test.core.db;

import lombok.Data;

/**
 * Created by Administrator on 2017/7/16.
 */
@Data
public class SqlConfig {
    private String name;
    //默认mysql 驱动
    private String driver="com.mysql.cj.jdbc.Driver";
    private String url;
    private String username;
    private String password;


    public SqlConfig builderUrl(String url){
        this.url=url;
        return this;
    }

    public SqlConfig builderUsername(String username){
        this.username=username;
        return this;
    }

    public SqlConfig builderPassWord(String password){
        this.password=password;
        return this;
    }



}
