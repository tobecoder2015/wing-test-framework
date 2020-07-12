package com.wing.test.testcode.api;


import com.wing.test.core.annotation.api.Api;
import com.wing.test.core.annotation.api.Param;
import com.wing.test.core.annotation.api.ParamType;
import com.wing.test.core.annotation.assertion.HeaderRule;
import com.wing.test.core.api.HtmlApi;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/20.
 */
@Component
public class ApiDemo extends HtmlApi {

    public ApiDemo(){
        super();
    }

    @HeaderRule(header = "")
    @Api(name = "hello",path = "/hello",description = "测试一个接口")
    public String hello(@Param(name = "name",type = ParamType.QUERY) String name) throws Exception{
        return get();
    }

}
