package com.wing.test.core.httpsender.sender;

import com.wing.test.core.httpsender.cookie.HostCookieStore;
import com.wing.test.core.httpsender.request.*;
import com.wing.test.core.httpsender.response.ResponseHandler;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/18.
 */
public abstract class HttpCoreSender<T> implements IHttpSender {

    private static final OkHttpClient CLIENT=new OkHttpClient();

    //定义各种类型的发送建造者
    protected  Map<String,IRequestBuilder> builders=new HashMap<>();

    //定义响应处理器
    protected ResponseHandler responseHandler;


    //请求信息
    protected RequestInfo requestInfo=new RequestInfo();


    private OkHttpClient.Builder clientBuilder;

    //初始化时重新构建一个builder
    public HttpCoreSender(){
        this.clientBuilder=CLIENT.newBuilder();
        builders.put("get",new GetBuilder());
        builders.put("post",new PostBuilder());
        builders.put("put",new PutBuilder());
        builders.put("delete",new DeleteBuilder());
        builders.put("postjson",new PostDataBuilder(MediaTypeEnum.application_json.value()));


    }

    //设置连接超时时间
    public void setConnectionTimeout(long timeout, TimeUnit timeUnit){
        this.clientBuilder.connectTimeout(timeout,timeUnit);
    }

    //设置读取超时时间
    public void setReadTimeout(long timeout, TimeUnit timeUnit){
        this.clientBuilder.readTimeout(timeout,timeUnit);
    }

    //设置写超时时间
    public void setWriteTimeout(long timeout, TimeUnit timeUnit){
        this.clientBuilder.writeTimeout(timeout,timeUnit);
    }


    public void setFollowRedirects(boolean followRedirects){
        this.clientBuilder.followRedirects(followRedirects);
    }


    public  OkHttpClient.Builder getClientBuilder(){return this.clientBuilder;}

    /**
     *  添加新的请求建造者
     * @param type
     * @param requestBuilder
     */
    public void addRequestBuilder(String type,IRequestBuilder requestBuilder){
        this.builders.put(type,requestBuilder);
    }

    /**添加拦截器
     *
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor){
        this.clientBuilder.addInterceptor(interceptor);
    }

    public void setResponseHandler(ResponseHandler responseHandler){
        this.responseHandler=responseHandler;
    }

    public T invorkApi(String requestType) throws Exception {
        return  invorkApi(this.requestInfo.getApi(),requestType);
    }

    @Override
    public T invorkApi(String api, String requestType) throws Exception {
        this.clientBuilder.cookieJar(new HostCookieStore());
        requestType=requestType.toLowerCase();

        if(!this.builders.containsKey(requestType)){
            throw  new IllegalArgumentException("unable to find request builder for type "+requestType);
        }else {
            //解决 url端口号后/重复导致请求失败问题
            if(api.startsWith("/"))
                api=api.substring(1);
            importPlatformInfo();
            this.requestInfo.setApi(api);
            IRequestBuilder requestBuilder=this.builders.get(requestType);
            Response response=this.clientBuilder.build().newCall(requestBuilder.build(requestInfo)).execute();
            this.restore();
            return (T)responseHandler.headle(response);
        }
    }

    public void restore(){
        this.requestInfo.getHeaders().clear();
        this.requestInfo.getFiles().clear();
        this.requestInfo.getQueryParams().clear();
        this.requestInfo.getParams().clear();

    }

    public void importPlatformInfo(){
        if(requestInfo.getContext().getPlatformHeader()!=null)
            requestInfo.headers(requestInfo.getContext().getPlatformHeader());

        if(requestInfo.getContext().getPlatformParam()!=null)
            requestInfo.params(requestInfo.getContext().getPlatformParam());

        if(requestInfo.getContext().getPlatformQueryParam()!=null)
            requestInfo.queryParams(requestInfo.getContext().getPlatformQueryParam());
    }



    @Override
    public void putQueryParam(String key, String value) {
        requestInfo.putQueryParam(key,value);
    }

    @Override
    public void queryParams(Map<String, String> queryParams) {
       requestInfo.queryParams(queryParams);
    }

    @Override
    public void setContext(ISenderContext senderContext) {
      requestInfo.setContext(senderContext);
    }

    @Override
    public void putHeader(String key, String value) {
       requestInfo.putHeader(key,value);
    }

    @Override
    public void headers(Map<String, String> headers) {
        requestInfo.headers(headers);
    }

    @Override
    public void putParam(String key, String value) {
       requestInfo.putParam(key,value);
    }

    @Override
    public void params(Map<String, String> params) {
      requestInfo.params(params);
    }

    @Override
    public void putFile(String key, File file) {
         requestInfo.putFile(key,file);
    }

    @Override
    public void files(Map<String, File> files) {
        requestInfo.fils(files);
    }

    @Override
    public void setApi(String api) {
      requestInfo.setApi(api);
    }


    @Override
    public void setRequestBody(String body) {
        requestInfo.setRequestBody(body.getBytes(Charset.forName("utf-8")));
    }


    public void clearCookie() {
        HostCookieStore.clearCookies();
    }
}
