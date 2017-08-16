package com.wing.test.core.httpsender.response;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/18.
 */
@Slf4j
public  class JSONBodyHandler extends ResponseBodyHandler<JSON> {


    public JSONBodyHandler(){}

    public JSON headle(Response response) throws Exception {
       if(!response.isSuccessful() && !response.isRedirect()){
           log.error(response.toString());
           throw new Exception("response fail");
       }else{
           return response.body()!=null?(JSON)JSON.parse(response.body().string()):null;
       }
    }
}
