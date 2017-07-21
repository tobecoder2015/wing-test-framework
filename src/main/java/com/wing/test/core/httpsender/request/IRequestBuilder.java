package com.wing.test.core.httpsender.request;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IRequestBuilder {
    Request build(RequestInfo requestInfo) throws Exception;
}
