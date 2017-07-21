package com.wing.test.core.httpsender.request.bodyBuilder;

import com.wing.test.core.httpsender.request.MediaTypeEnum;
import com.wing.test.core.httpsender.request.RequestInfo;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/18.
 */
public class FormBodyBuilder implements IBodyBuilder {



    public FormBodyBuilder(){}


    @Override
    public RequestBody buildBody(RequestInfo requestInfo) {
        FormBody.Builder formBodyBuilder=new FormBody.Builder();

        Iterator iterator=requestInfo.getParams().keySet().iterator();
        while (iterator.hasNext()){
            String key=(String) iterator.next();
            formBodyBuilder.add(key,(String)requestInfo.getParams().get(key));
        }

        return formBodyBuilder.build();
    }
}
