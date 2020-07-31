package com.wing.test.demo.api;


import com.alibaba.fastjson.JSON;
import com.wing.test.core.annotation.api.Api;
import com.wing.test.core.api.JsonApi;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/20.
 */
@Component
public class ApiJsonDemo extends JsonApi {

    public ApiJsonDemo(){
        super();
    }

    @Api(name = "json",path = "/json",description = "测试一个JSON接口")
    public JSON json() throws Exception{
        return get();
    }

}
