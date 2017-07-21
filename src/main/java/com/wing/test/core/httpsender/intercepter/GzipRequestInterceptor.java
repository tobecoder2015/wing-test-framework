package com.wing.test.core.httpsender.intercepter;

import okhttp3.*;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/17.</>
 */
public class GzipRequestInterceptor implements Interceptor {

    public GzipRequestInterceptor(){}
    public Response intercept(Chain chain) throws IOException{
        Request originalRequest=chain.request();
        if(originalRequest.body()!=null && originalRequest.header("Content-Encoding")==null){
            Request compressedRequest=originalRequest.newBuilder().header("Content-Encoding","gzip").method(originalRequest.method(),this.gzip(originalRequest.body())).build();
            return chain.proceed(compressedRequest);
        }else {
            return chain.proceed(originalRequest);
        }
    }

    private RequestBody gzip(final RequestBody body){
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BufferedSink gzipSink= Okio.buffer(new GzipSink(bufferedSink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }



}
