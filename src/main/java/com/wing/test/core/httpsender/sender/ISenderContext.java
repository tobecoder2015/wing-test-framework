package com.wing.test.core.httpsender.sender;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface ISenderContext {

    String getHost();
    int getPort();
    String getSchema();
    Map<String,String> getPlatformParam();
    Map<String,String> getPlatformQueryParam();
    Map<String,String> getPlatformHeader();
}
