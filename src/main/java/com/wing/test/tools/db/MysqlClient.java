package com.wing.test.tools.db;


/**
 * Created by Administrator on 2017/7/20.
 */
public class MysqlClient<T> extends SqlClient<T> {

  public MysqlClient(SqlConfig config){
      super(config);
  }
}
