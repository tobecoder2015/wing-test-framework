package com.wing.test.core.annotationProcessor;

import com.wing.test.core.annotation.LoopCheck;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/7/19.
 */
public class LoopCheckProcessor implements MethodInterceptor {

    private static Logger logger= LogManager.getLogger(LoopCheckProcessor.class);
    @Override
    public Object intercept(Object object, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        LoopCheck loopCheck = method.getAnnotation(LoopCheck.class);
        if (loopCheck == null)
            return methodProxy.invoke(object, params);
        else
            return loopCheck(object,method,params,methodProxy,loopCheck);
    }

    private Object loopCheck(Object object, Method method, Object[] params, MethodProxy methodProxy,LoopCheck loopCheck) throws Throwable{
        Object result=null;
        int time=0;
        long startTime=System.currentTimeMillis();
        while(System.currentTimeMillis()-startTime<=loopCheck.timeout()){
            try {
                result=methodProxy.invokeSuper(object,params);
            } catch (Throwable e) {
               logger.error(e.getMessage());
            }
            Thread.sleep(loopCheck.interval());
        }
        return result;
    }


}
