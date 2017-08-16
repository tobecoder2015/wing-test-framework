package com.wing.test.core.api;

import com.wing.test.core.httpsender.request.RequestTypeEnum;
import com.wing.test.core.httpsender.sender.HttpCoreSender;
import com.wing.test.core.httpsender.sender.ISenderContext;

/**
 * Created by Administrator on 2017/7/18.
 */
public abstract  class AbstractApi<T> {
    public HttpCoreSender<T> sender;

    public AbstractApi(HttpCoreSender<T> sender){
        this.sender=sender;
    }

    public void init(ISenderContext senderContext){
        this.sender.setContext(senderContext);
    }

    public void setSender(HttpCoreSender<T> sender){this.sender=sender;}

    public HttpCoreSender<T> getSender(){return sender;}

    public T get() throws Exception{
        return this.sender.invorkApi(RequestTypeEnum.GET.name());
    }

    public T post() throws Exception{
        return this.sender.invorkApi(RequestTypeEnum.POST.name());
    }


    public T postJson() throws Exception{
        return this.sender.invorkApi(RequestTypeEnum.PostJson.name());
    }

    public T delete() throws Exception{
        return this.sender.invorkApi(RequestTypeEnum.DELETE.name());
    }

    public T multipart() throws Exception{
        return this.sender.invorkApi(RequestTypeEnum.MULTIPART.name());
    }
    public void clearCookie(){this.sender.clearCookie();}

}
