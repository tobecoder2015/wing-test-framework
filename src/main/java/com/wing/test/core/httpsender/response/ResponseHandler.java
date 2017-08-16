package com.wing.test.core.httpsender.response;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/18.
 */
public abstract class ResponseHandler<T> {
    public ResponseHandler(){}

    public abstract T headle(Response response) throws Exception;
}
