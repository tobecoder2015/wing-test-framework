package com.wing.test.demo;

import com.alibaba.fastjson.JSON;
import com.wing.test.tools.common.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/7/21.
 */
@Test
@Slf4j
public class ApiDemoTest extends TestBase{

    @Test
    public  void test3() throws Exception{
        System.out.println(ConfigManager.getString("db_url"));
      
    }

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


    @DataProvider(name = "aa")
    public Object[][] get(){
        return  new Object[][]{{1,2,3},{4,5,6}};
    }

    @Test(dataProvider = "aa")
    public  void test3(int a,int b,int c) throws Exception{
        log.info((a+b+c)+"");
    }
}