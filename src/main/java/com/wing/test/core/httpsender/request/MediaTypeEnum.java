package com.wing.test.core.httpsender.request;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/7/18.
 */
public enum MediaTypeEnum {
    application_steam(MediaType.parse("application/octet-stream")),
    application_json(MediaType.parse("application/json;charset=utf-8")),
    text_xml(MediaType.parse("text/xml;charset=utf-8")),
    img_jpg(MediaType.parse("img/jpeg")),
    img_png(MediaType.parse("img/png")),
    img_gif(MediaType.parse("img/gif"));

    private MediaType mediaType;

    MediaTypeEnum(MediaType mediaType){
        this.mediaType=mediaType;
    }

    public MediaType value(){return this.mediaType;}
}
