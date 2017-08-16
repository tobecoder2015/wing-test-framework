package com.wing.test.core.httpsender.intercepter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.lang.invoke.MutableCallSite;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/17.</>
 */
@Slf4j
public class JSONLogInterceptor implements Interceptor {

    public JSONLogInterceptor(){}
    public Response intercept(Chain chain) throws IOException{
        Request request=chain.request();
        log.info("Request","[URL]:"+request.url().toString());

        RequestBody requestBody=request.body();
        if(requestBody!=null && request.method().equalsIgnoreCase("post")){
            if(requestBody instanceof FormBody){
                FormBody response=(FormBody)requestBody;
                for(int mediaType=0;mediaType<response.size();mediaType++){
                    log.info("Request","[Form-Body]:"+response.name(mediaType)+": "+response.value(mediaType));
                }
            }else if(requestBody instanceof MultipartBody){
                MultipartBody multipartBody=(MultipartBody) requestBody;
                Iterator var1=multipartBody.parts().iterator();
                while (var1.hasNext()){
                    MultipartBody.Part var2=(MultipartBody.Part)var1.next();
                    log.info("Request","[Multipart-Header]:"+var2.headers().toString());
                }
            }else {
                log.info("Request","[Body]:"+requestBody.toString());
            }
        }

        Response response=chain.proceed(request);
        MediaType responseType=response.body().contentType();
        byte[] responseByte=response.body().bytes();
        JSON result=(JSON)JSON.parse(responseByte,new Feature[0]);

        log.info("Response",JSON.toJSONString(result,true));
        return response.newBuilder().body(ResponseBody.create(responseType,responseByte)).build();
    }



}
