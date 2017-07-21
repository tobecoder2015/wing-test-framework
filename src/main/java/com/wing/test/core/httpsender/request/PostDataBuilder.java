package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.request.bodyBuilder.DataBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.FormBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.IBodyBuilder;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/7/18.
 */
public class PostDataBuilder extends PostBuilder {

    public PostDataBuilder(MediaType mediaType,byte[] data){this.setBodyBuilder(new DataBodyBuilder(mediaType,data));}
    public PostDataBuilder(MediaType mediaType){this.setBodyBuilder(new DataBodyBuilder(mediaType));}
    public  void setData(byte[] data){this.setBodyBuilder(new DataBodyBuilder(data));}

}
