package com.wing.test.core.httpsender.sender;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface ISender<T> {
    T invorkApi(String var1,String var2) throws Exception;

    void setContext(ISenderContext senderContext);
}