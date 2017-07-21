package com.wing.test.core.httpsender.request;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/18.
 */
public abstract  class RequestBuilder implements IRequestBuilder {

    protected Request.Builder requestBuilder =new Request.Builder();
    protected HttpUrl.Builder urlBuilder=null;

    public RequestBuilder(){}

    public Request.Builder getRequestBuilder(){return this.requestBuilder;};

    private void addHeaders(Map<String,String> headers){
        Headers.Builder headBuilder=new Headers.Builder();
        Iterator iterator=headers.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry entry=(Map.Entry) iterator.next();
            headBuilder.add((String) entry.getKey(),(String) entry.getValue());
        }
        this.requestBuilder.headers(headBuilder.build());
    }

    private String preHandleApi(String api,Map<String,String> params) throws Exception{
        Pattern pattern=Pattern.compile("\\{#\\w+\\}");
        Matcher matcher=pattern.matcher(api);
        String newApi=api;

        while (matcher.find()){
            String target=matcher.group();
            String key=target.substring(2,target.length()-1);
            if(!params.containsKey(key)){
                throw new IllegalArgumentException("unable to find "+target+" ,please check your api");
            }

             newApi=api.replace("\\"+target,(String)params.get(key));
            params.remove(key);

        }
        return newApi;
    }

    protected void buildUrl(RequestInfo requestInfo){
        Iterator iterator=requestInfo.getQueryParams().keySet().iterator();

        while(iterator.hasNext()){
            String key=(String) iterator.next();
            String value=requestInfo.getQueryParams().get(key);
            try {
                this.urlBuilder.addEncodedQueryParameter(key, URLEncoder.encode(value,"utf-8"));
            }catch (UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
        }
    }

    public Request build(RequestInfo requestInfo) throws Exception{
        this.addHeaders(requestInfo.getHeaders());
        requestInfo.setApi(this.preHandleApi(requestInfo.getApi(),requestInfo.getQueryParams()));
        this.urlBuilder=(new HttpUrl.Builder().scheme(requestInfo.getContext().getSchema()).host(requestInfo.getContext().getHost()))
                .port(requestInfo.getContext().getPort()).addEncodedPathSegment(requestInfo.getApi());
        this.buildUrl(requestInfo);
        String url=urlBuilder.build().toString();
        return  this.requestBuilder.url(this.urlBuilder.build()).build();
    }

    public abstract Request buildRequest(RequestInfo requestInfo) throws Exception;
}
