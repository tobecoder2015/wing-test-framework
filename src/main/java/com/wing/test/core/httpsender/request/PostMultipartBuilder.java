package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.request.bodyBuilder.DataBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.MultipartBodyBuilder;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/7/18.
 */
public class PostMultipartBuilder extends PostBuilder {

    public PostMultipartBuilder(MediaType mediaType){super(new MultipartBodyBuilder(mediaType));}
    public PostMultipartBuilder(){super(new MultipartBodyBuilder(MediaTypeEnum.application_steam.value()));}

    public  void setMediaType(MediaType mediaType){((MultipartBodyBuilder)this.getBodyBuilder()).setMediaType(mediaType);}

}
