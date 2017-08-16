package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.sender.ISenderContext;
import lombok.Data;
import okhttp3.Request;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;

/**
 * Created by Administrator on 2017/7/18.
 */


public class RequestInfo {
    private ISenderContext context;
    private String api;
    private Map<String,String> headers=new ConcurrentHashMap();
    private Map<String,String> params=new ConcurrentHashMap();
    private Map<String,File> files=new ConcurrentHashMap();
    private Map<String,String> queryParams=new ConcurrentHashMap();
    private byte[] requestBody;

    public RequestInfo(){}

    public ISenderContext getContext(){
        return this.context;
    }
    public void setContext(ISenderContext sendContext){this.context=sendContext;}

    public String getApi(){return this.api;}
    public void setApi(String api){this.api=api;}

    public void putHeader(String key,String value){this.headers.put(key,value);}
    public void headers(Map<String,String> headers){this.headers.putAll(headers);}
    public Map<String,String> getHeaders(){return this.headers;}

    public void putParam(String key,String value){this.params.put(key,value);}
    public void params(Map<String,String> params){this.params.putAll(params);}
    public Map<String,String> getParams(){return this.params;}


    public void putQueryParam(String key,String value){this.queryParams.put(key,value);}
    public void queryParams(Map<String,String> queryParams){this.queryParams.putAll(queryParams);}
    public Map<String,String> getQueryParams(){return this.queryParams;}


    public void putFile(String key,File file){this.files.put(key,file);}
    public void fils(Map<String,File> files){this.files.putAll(files);}
    public Map<String,File> getFiles(){return this.files;}

    public void setRequestBody(byte[] requestBody){this.requestBody=requestBody;}
    public byte[] getRequestBody(){return this.requestBody;}

}
