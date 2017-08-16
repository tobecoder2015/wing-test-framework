package com.wing.test.core.httpsender.sender;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IHttpSender  extends ISender {
    void putHeader(String key,String value);
    void headers(Map<String,String> headers);
    void putParam(String key,String value);
    void params(Map<String ,String> params);
    void putQueryParam(String key,String value);
    void queryParams(Map<String ,String> queryParams);
    void putFile(String key, File file);
    void files(Map<String,File> files);
    void setApi(String api);
    void setRequestBody(String body);

}
