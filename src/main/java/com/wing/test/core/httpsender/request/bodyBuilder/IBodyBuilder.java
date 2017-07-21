package com.wing.test.core.httpsender.request.bodyBuilder;

import com.wing.test.core.httpsender.request.RequestInfo;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IBodyBuilder {
    RequestBody buildBody(RequestInfo requestInfo);
}
