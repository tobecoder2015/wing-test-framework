package com.wing.test;

import com.alibaba.fastjson.JSON;
import com.wing.test.base.TestBase;
import com.wing.test.core.BeanContainer;
import com.wing.test.testcode.api.ApiDemo;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by Administrator on 2017/7/21.
 */
@Test
@Slf4j
public class ApiDemoTest extends TestBase{

    @Test
    public  void test() throws Exception{
        String result=user.apiDemo.hello("wqs");
        log.info(result);
        Assert.assertEquals(result,"hello wqs");
    }


    @Test
    public  void test2() throws Exception{
        JSON result=user.apiJsonDemo.json();
        log.info(result.toJSONString());
    }
}
