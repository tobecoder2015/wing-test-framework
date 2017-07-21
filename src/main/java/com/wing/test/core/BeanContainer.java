package com.wing.test.core;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/20.
 */
@Configuration
@EnableAspectJAutoProxy
public  class BeanContainer {

    static ApplicationContext appCtx=new AnnotationConfigApplicationContext("com.wing.test");


    public static  Object getBean(String beanName){
        return appCtx.getBean(beanName);
    }


    public static  Object getBean(Class clz){
        return appCtx.getBean(clz);
    }

}
