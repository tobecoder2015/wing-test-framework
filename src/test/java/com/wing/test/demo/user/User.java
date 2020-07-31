package com.wing.test.demo.user;

import com.wing.test.core.user.AbstractUser;
import com.wing.test.core.httpsender.sender.ISenderContext;
import com.wing.test.demo.api.ApiDemo;
import com.wing.test.demo.api.ApiJsonDemo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/21.
 */
@Component
public class User  extends AbstractUser{

    @Resource
    public ApiDemo apiDemo;

    @Resource
    public ApiJsonDemo apiJsonDemo;

    @Override
    public void initApi(ISenderContext senderContext) {
        apiDemo.init(senderContext);
        apiJsonDemo.init(senderContext);
    }
}
