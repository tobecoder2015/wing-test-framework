package com.wing.test.tools.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/20.
 */
public abstract  class SqlClient<T> {

    private SqlConfig sqlConfig;
    private   BasicDataSource bds = null;
    private  QueryRunner qr=null;


    public SqlClient(SqlConfig sqlConfig){
        this.sqlConfig=sqlConfig;
        bds = new BasicDataSource();
        //设置连接池所需的驱动
        bds.setDriverClassName(this.sqlConfig.getDriver());
        bds.setUrl(this.sqlConfig.getUrl());
        bds.setUsername(this.sqlConfig.getUsername());
        bds.setPassword(this.sqlConfig.getPassword());
        //设置连接池的初始连接数
        bds.setInitialSize(this.sqlConfig.getInitialSize());
        //设置连接池最多可以有多少个活动连接数
        bds.setMaxActive(this.sqlConfig.getMaxActive());
        //设置连接池最少有两个空闲的连接
        bds.setMinIdle(this.sqlConfig.getMinIdle());
        qr=new QueryRunner(bds);
    }

    boolean update(String sql) throws Exception{
        return qr.update(sql)>0;
    }

    List<Map<String,Object>>  mapList(String sql) throws Exception{
        return   qr.query(sql, new MapListHandler());
    }

    Map<String,Object>  map(String sql) throws Exception{
        return   qr.query(sql, new MapHandler());
    }

    List<T> beanList(String sql, Class<T> t) throws Exception{
        return   qr.query(sql, new BeanListHandler<T>(t));
    }

    List<T> fieldList(String sql,String columnName) throws Exception{
        return   qr.query(sql, new ColumnListHandler<T>(columnName));
    }
}
