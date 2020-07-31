package com.wing.test.tools.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2020/7/12.
 */

public class ConfigManager {
    private static Logger logger= LogManager.getLogger(ConfigManager.class);
    private static  Properties props;

    static {
        props=new Properties();
        InputStream is=null;
        try {
             is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(is);
        }catch (Exception e){
            logger.error("配置文件不存在");
        }
    }

    public static  String getString(String key){
        return props.getProperty(key);
    }

    public static  Boolean getBoolean(String key){
        return  Boolean.getBoolean(props.getProperty(key));
    }

    public static  Integer getInt(String key){
        return Integer.parseInt(props.getProperty(key));
    }

}
