package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.request.bodyBuilder.FormBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.IBodyBuilder;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/7/18.
 */
public class PostBuilder extends RequestBuilder {


    private IBodyBuilder bodyBuilder;
    public PostBuilder(){ this.bodyBuilder=new FormBodyBuilder(); }
    public PostBuilder(IBodyBuilder bodyBuilder){this.bodyBuilder=bodyBuilder;}


    public IBodyBuilder getBodyBuilder(){return this.bodyBuilder;}
    public void setBodyBuilder(IBodyBuilder bodyBuilder){this.bodyBuilder=bodyBuilder;}

    @Override
    public Request build(RequestInfo requestInfo) throws Exception {
        this.buildUrl(requestInfo);

        return this.requestBuilder.url(this.urlBuilder.build()).post(this.bodyBuilder.buildBody(requestInfo)).build();
    }
}
