package com.wing.test.tools.db;

import com.wing.test.tools.common.ConfigManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2020/7/12.
 */
public class DbUtil {
    private static Logger logger= LogManager.getLogger(ConfigManager.class);

    private static Map<String,SqlClient> clientMap=new ConcurrentHashMap<>();

    public static SqlClient getClient(String prefix){
        SqlClient sqlClient=null;
        synchronized (clientMap){
            sqlClient=(SqlClient) clientMap.get(prefix);
            try {
                if(sqlClient==null){
                    sqlClient=createClient(prefix);
                }
            }catch (Exception e){
                logger.error(e);
                throw e;
            }
            return sqlClient;
        }
    }

    private static  String getKey(String prefix,String key){
        return prefix+"_"+key;
    }
    public static SqlClient createClient(String prefix){
        SqlClient sqlClient=null;
            try {
                if(sqlClient==null){
                    SqlConfig sqlConfig=  new SqlConfig();
                    sqlConfig.setUrl(ConfigManager.getString(getKey(prefix,"url")));
                    sqlConfig.setPassword(ConfigManager.getString(getKey(prefix,"password")));
                    sqlConfig.setUsername(ConfigManager.getString(getKey(prefix,"username")));
                    if(sqlConfig.getUrl().contains("mysql")) {
                        sqlConfig.setDriver("com.mysql.jdbc.Driver");
                        return new MysqlClient(sqlConfig);
                    }
                }
            }catch (Exception e){
                logger.error("创建数据库失败",e);
            }
        return sqlClient;
    }



}
