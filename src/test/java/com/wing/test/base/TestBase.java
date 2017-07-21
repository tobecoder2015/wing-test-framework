package com.wing.test.base;

import com.wing.test.core.BeanContainer;
import com.wing.test.core.httpsender.sender.BaseSenderContext;
import com.wing.test.testcode.user.User;
import org.testng.annotations.*;

/**
 * Created by Administrator on 2017/7/21.
 */
@Test
public class TestBase {
    protected User user;

      @BeforeSuite
      @Parameters({ "host", "port" })
      public  void init(@Optional("localhost") String host,
                        @Optional("8080") int port) throws Exception{

          BaseSenderContext baseSenderContext=new BaseSenderContext();
          baseSenderContext.setPort(port);

          user=(User) BeanContainer.getBean(User.class);
          user.initApi(baseSenderContext);
          String result=user.apiDemo.hello("wqs");
          System.out.println();

      }

}
