package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.request.bodyBuilder.FormBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.IBodyBuilder;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/18.
 */
public class GetBuilder extends RequestBuilder {


    public GetBuilder(){}


    @Override
    public Request buildRequest(RequestInfo requestInfo) throws Exception {
        Map<String,String> params=new HashMap();
        params.putAll(requestInfo.getParams());
        params.putAll(requestInfo.getQueryParams());

        Iterator iterator=params.keySet().iterator();
        while (iterator.hasNext()){
            String key=(String) iterator.next();
            String value=(String) params.get(key);
            try {
                this.urlBuilder.addEncodedQueryParameter(key, URLEncoder.encode(value,"utf-8"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return this.requestBuilder.url(this.urlBuilder.build()).get().build();
    }
}
