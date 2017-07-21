package com.wing.test.core.httpsender.request;

import com.wing.test.core.httpsender.request.bodyBuilder.DataBodyBuilder;
import com.wing.test.core.httpsender.request.bodyBuilder.FormBodyBuilder;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/7/18.
 */
public class PostFormBuilder extends PostBuilder {

    public   PostFormBuilder(){super(new FormBodyBuilder());}

}
