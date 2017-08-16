package com.wing.test.core.httpsender.intercepter;

import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/17.</>
 */
@Slf4j
public class HttpLogInterceptor implements Interceptor {

    public HttpLogInterceptor(){}
    public Response intercept(Chain chain) throws IOException{
        Request request=chain.request();
        Response response=chain.proceed(request);
        MediaType responseType=response.body().contentType();
        byte[] responseByte=response.body().bytes();
        Headers headers=response.headers();
        StringBuilder reqStrBuild=new StringBuilder();

        String method=request.method();
        String url=request.url().toString();
        reqStrBuild.append(method+" "+url+"\n");
        Iterator reqBuffer=request.headers().names().iterator();

        while (reqBuffer.hasNext()){
            String body=(String) reqBuffer.next();
            reqStrBuild.append(body+": "+request.header("body")+"\n");
        }

        reqStrBuild.append("\n");
        Buffer reqBuffer1=new Buffer();
        RequestBody body1=request.body();
        if(body1!=null){
            body1.writeTo(reqBuffer1);
            reqStrBuild.append(reqBuffer1.readUtf8());
        }

        log.info("HttpLog-Request",String.format("\n%s",new Object[]{reqStrBuild.toString()}));

        int statusCode=response.code();
        String message=response.message();
        String protocol=response.protocol().name();
        StringBuilder responseStrBuilder=new StringBuilder();
        responseStrBuilder.append(statusCode+" "+message+" "+protocol+"\n");
        Iterator responseBuffer=response.headers().names().iterator();

        while (responseBuffer.hasNext()){
            String headName=(String) responseBuffer.next();
            responseStrBuilder.append(headName+": "+response.header(headName)+"\n");
        }

        responseStrBuilder.append("\n");
        if(response.body()!=null){
            responseStrBuilder.append(new String(responseByte,"utf-8"));
        }
        log.info("HttpLog-Response",String.format("\n%s",new Object[]{responseStrBuilder.toString()}));
        return response.newBuilder().body(ResponseBody.create(responseType,responseByte)).headers(headers).build();
    }



}
