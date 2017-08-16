package com.wing.test.core.httpsender.request.bodyBuilder;

import com.wing.test.core.httpsender.request.MediaTypeEnum;
import com.wing.test.core.httpsender.request.RequestInfo;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/7/18.
 */
public class DataBodyBuilder implements IBodyBuilder {

    private MediaType mediaType=null;

    private byte[] data=null;

    public DataBodyBuilder(){this.mediaType= MediaTypeEnum.application_steam.value();}
    public DataBodyBuilder(MediaType mediaType){this.mediaType= mediaType;}
    public DataBodyBuilder( byte[] data){this.data= data;}

    public DataBodyBuilder(MediaType mediaType,byte[] data){this.mediaType= mediaType;this.data= Arrays.copyOf(data,data.length);}

    public void setData(byte[] data){this.data=Arrays.copyOf(data,data.length);}

    public void setMediaType(MediaType mediaType){this.mediaType=mediaType;}
    public  MediaType getMediaType(){return  this.mediaType;}



    @Override
    public RequestBody buildBody(RequestInfo requestInfo) {
        if(this.data!=null)
            requestInfo.setRequestBody(this.data);
        return RequestBody.create(this.mediaType,requestInfo.getRequestBody());
    }
}
