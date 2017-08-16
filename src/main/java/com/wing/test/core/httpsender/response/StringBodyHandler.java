package com.wing.test.core.httpsender.response;

import com.alibaba.fastjson.JSON;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/18.
 */
public  class StringBodyHandler<T> extends ResponseBodyHandler<String>{


    public StringBodyHandler(){}

    public String headle(Response response) throws Exception {
       if(!response.isSuccessful() && !response.isRedirect()){
           throw new Exception("response fail");
       }else{
           return response.body()!=null?response.body().string():null;
       }
    }
}
