package com.wing.test.core.httpsender.response;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
public class HttpResponse<T> {
    private int code;
    private String protocal;
    private Map<String,String> headers;
    private T body;


    public HttpResponse(int code,String protocal,Map<String,String> headers,T body){
        this.code=code;
        this.protocal=protocal;
        this.headers=headers;
        this.body=body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
