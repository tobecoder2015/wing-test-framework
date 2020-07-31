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
public class CommonTest {

    @Test
    public  void test3() throws Exception{
        System.out.println(ConfigManager.getString("db_url"));
      
    }


}
