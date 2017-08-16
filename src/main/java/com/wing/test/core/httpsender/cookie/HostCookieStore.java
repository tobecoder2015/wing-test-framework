package com.wing.test.core.httpsender.cookie;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/17.</>
 */
public class HostCookieStore implements CookieJar {
    private static Map<String,List<Cookie>> COOKE_STORE=new ConcurrentHashMap();

    public HostCookieStore(){}

    public void saveFromResponse(HttpUrl url, List<Cookie> cookies){COOKE_STORE.put(url.host(),cookies);};


    public List<Cookie> loadForRequest(HttpUrl url){
        List cookies=COOKE_STORE.get(url.host());
        return (List)(cookies!=null?cookies:new ArrayList());
    }
    public static  void clearCookies() {COOKE_STORE.clear();}
}
