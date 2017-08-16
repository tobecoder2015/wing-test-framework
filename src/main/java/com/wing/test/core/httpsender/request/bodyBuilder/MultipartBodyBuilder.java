package com.wing.test.core.httpsender.request.bodyBuilder;

import com.wing.test.core.httpsender.request.MediaTypeEnum;
import com.wing.test.core.httpsender.request.RequestInfo;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/18.
 */
public class MultipartBodyBuilder implements IBodyBuilder {

    private MediaType mediaType=null;


    public MultipartBodyBuilder(){this.mediaType= MediaTypeEnum.application_steam.value();}
    public MultipartBodyBuilder(MediaType mediaType){this.mediaType= mediaType;}


    public void setMediaType(MediaType mediaType){this.mediaType=mediaType;}
    public  MediaType getMediaType(){return  this.mediaType;}



    @Override
    public RequestBody buildBody(RequestInfo requestInfo) {
        MultipartBody.Builder multipartBuilder=new MultipartBody.Builder();
        multipartBuilder.setType(MultipartBody.FORM);


        Iterator iterator=requestInfo.getParams().keySet().iterator();
        while (iterator.hasNext()){
            String key=(String) iterator.next();
            multipartBuilder.addFormDataPart(key,(String)requestInfo.getParams().get(key));
        }

        iterator=requestInfo.getFiles().keySet().iterator();
        while (iterator.hasNext()){
            String key=(String) iterator.next();
            multipartBuilder.addFormDataPart(key,requestInfo.getFiles().get(key).getName(),RequestBody.create(this.mediaType,requestInfo.getFiles().get(key)));
        }

        return multipartBuilder.build();
    }
}
