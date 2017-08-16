package com.wing.test.core.httpsender.request;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/7/18.
 */
public class GetBuilder extends RequestBuilder {


    public GetBuilder(){}


    @Override
    public Request build(RequestInfo requestInfo) throws Exception {
        requestInfo.queryParams(requestInfo.getParams());
        this.buildUrl(requestInfo);
        return this.requestBuilder.url(this.urlBuilder.build()).get().build();
    }
}
