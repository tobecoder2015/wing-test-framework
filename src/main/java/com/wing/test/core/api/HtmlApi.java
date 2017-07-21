package com.wing.test.core.api;

import com.wing.test.core.httpsender.response.StringBodyHandler;
import com.wing.test.core.httpsender.sender.HtmlSender;

/**
 * Created by Administrator on 2017/7/18.
 */
public   class HtmlApi extends AbstractApi<String> {

    public HtmlApi(){
        super(new HtmlSender());
        super.sender.setResponseHandler(new StringBodyHandler());
    }
}
