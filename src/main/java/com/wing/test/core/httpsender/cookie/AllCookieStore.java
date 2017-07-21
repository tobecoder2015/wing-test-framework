package com.wing.test.core.httpsender.cookie;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.</>
 */
public class AllCookieStore implements CookieJar {
    private static List<Cookie> COOKE_STORE=new ArrayList();

    public AllCookieStore(){}

    public void saveFromResponse(HttpUrl url, List<Cookie> cookies){COOKE_STORE.addAll(cookies);};

    public List<Cookie> loadForRequest(HttpUrl url){
        List cookies=COOKE_STORE;
        Integer a=new Integer(3);
        return (List)(cookies!=null?cookies:new ArrayList());

    }
    public void clearCookies() {COOKE_STORE.clear();}
}
