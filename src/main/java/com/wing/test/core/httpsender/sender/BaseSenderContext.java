package com.wing.test.core.httpsender.sender;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
public class BaseSenderContext implements ISenderContext {
    private String host="localhost";
    private  int port=80;
    private  String schema="http";
    private String url;

    private Map<String,String> platformParam=new HashMap();
    private Map<String,String> platformQueryParam=new HashMap();
    private Map<String,String> platformHeader=new HashMap();

    public BaseSenderContext(){}

    @Override
    public String getHost() {
        return this.host;
    }

    public void setHost(String host){
        this.host=host;
    }

    public BaseSenderContext setUrl(String url) throws Exception{
        this.url=url;
        URL targetUrl=new URL(url);
        this.host=targetUrl.getHost();
        if(targetUrl.getPort()!=-1)
            this.port=targetUrl.getPort();
        this.schema=targetUrl.getProtocol();

        return this;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    public BaseSenderContext setPort(int port){
        this.port=port;
        return this;
    }



    @Override
    public String getSchema() {
        return this.schema;
    }

    public BaseSenderContext setSchema(String schema){
        this.schema=schema;
        return  this;
    }

    @Override
    public Map<String, String> getPlatformParam() {
        return this.platformParam;
    }

    public BaseSenderContext putPlatformParam(String key, String value){
        this.platformParam.put(key,value);
        return  this;
    }

    @Override
    public Map<String, String> getPlatformQueryParam() {
        return this.platformQueryParam;
    }

    public BaseSenderContext putPlatformQueryParam(String key, String value){
            this.platformQueryParam.put(key,value);
            return  this;
    }

    @Override
    public Map<String, String> getPlatformHeader() {
        return this.platformHeader;
    }

    public BaseSenderContext putPlatformHeader(String key, String value){
        this.platformHeader.put(key,value);
        return  this;
    }
}
