package com.wing.test.core.httpsender.response;

import okhttp3.Headers;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/18.
 */
public  class HttpResponseHandler<T> extends ResponseHandler<HttpResponse<T>>{

    private ResponseBodyHandler<T> responseBodyHander;

    public HttpResponseHandler(ResponseBodyHandler<T> responseBodyHander){
        this.responseBodyHander=responseBodyHander;
    }

    public HttpResponse<T> headle(Response response) throws Exception {
        int e = response.code();
        String protocol = response.protocol().name();
        HashMap headers = new HashMap();

        Headers _headers = response.headers();
        Set headerNames = _headers.names();
        Iterator it = headerNames.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            headers.put(key, _headers.get(key));
        }
        Object body = this.responseBodyHander == null ? null : this.responseBodyHander.headle(response);
        return new HttpResponse(e, protocol, headers, body);
    }
}
