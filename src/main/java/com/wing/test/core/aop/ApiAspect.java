package com.wing.test.core.aop;

import com.wing.test.core.annotation.api.Api;
import com.wing.test.core.annotation.api.Param;
import com.wing.test.core.annotation.data.ExcelData;
import com.wing.test.core.api.AbstractApi;
import com.wing.test.core.httpsender.sender.HttpCoreSender;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/16.
 */
@Component
@Aspect
public class ApiAspect {
    public ApiAspect(){}

    @Pointcut("target(bean)&&@within(excelData)&&@annotation(api)")
    public void apiPointCutPrepareData(Object bean, ExcelData excelData,Api api){}


    @Pointcut("@annotation(api)")
    public void apiPointCutInitSender(Api api){}

    @Before("apiPointCutInitSender(api)")
    public void initSender(JoinPoint jp, Api api) throws NoSuchMethodException {
        HttpCoreSender sender = ((AbstractApi) jp.getTarget()).getSender();

        if (jp.getTarget() instanceof AbstractApi) {
            if (!StringUtils.isEmpty(api.path())) {
                sender.setApi(api.path());
            }
        }
        Object[] args = jp.getArgs();

        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Annotation[][] _annotations = method.getParameterAnnotations();
        for (int i = 0; i < args.length; i++) {
            Param param = this.getParamAnnotation(i, _annotations);
            if (param != null) {
                String paramName = param.name();
                String paramValue = args[i] != null ? String.valueOf(args[i]) : null;

                assert !StringUtils.isEmpty(paramName) : "Parameter name can't be empty";

                switch (param.type()) {
                    case FROM:
                        sender.putParam(paramName, paramValue);
                        break;
                    case PATH:
                        sender.putQueryParam(paramName, paramValue);
                        break;
                    case FILE:
                        sender.putFile(paramName, new File(paramValue));
                        break;
                    case HEADER:
                        sender.putHeader(paramName, paramValue);
                        break;
                    case BODY:
                        sender.setRequestBody(paramValue);
                        break;
                    case QUERY:
                        sender.putQueryParam(paramName, paramValue);
                        break;
                    default:
                        sender.putParam(paramName, paramValue);

                }
            }
        }
    }

    private Param getParamAnnotation(int argIndex,Annotation[][] annotations){
        Annotation[] _annotations=annotations[argIndex];
        if(_annotations==null){
            return null;
        }else{
            Annotation[] arr=_annotations;
            int len=arr.length;
            for(Annotation anno :arr){
                if(anno instanceof  Param)
                    return (Param)anno;
            }
        }
        return null;
    }
}
