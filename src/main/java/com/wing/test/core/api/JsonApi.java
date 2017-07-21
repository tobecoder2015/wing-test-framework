package com.wing.test.core.api;

import com.alibaba.fastjson.JSON;
import com.wing.test.core.httpsender.response.JSONBodyHandler;
import com.wing.test.core.httpsender.sender.JsonSender;

/**
 * Created by Administrator on 2017/7/18.
 */
public  abstract class JsonApi extends AbstractApi<JSON> {
    public JsonApi(){super(new JsonSender());
        super.sender.setResponseHandler(new JSONBodyHandler());
    }

}
