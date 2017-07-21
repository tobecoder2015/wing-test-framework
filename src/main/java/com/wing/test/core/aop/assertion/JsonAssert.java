package com.wing.test.core.aop.assertion;

import com.alibaba.fastjson.JSON;
import com.wing.test.core.annotation.assertion.JsonAssertions;
import com.wing.test.core.annotation.assertion.JsonRule;
import com.wing.test.core.annotation.assertion.Op;
import com.wing.test.core.httpsender.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hamcrest.Matchers;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.wing.test.core.annotation.assertion.Op.contains;
import static com.wing.test.core.annotation.assertion.Op.eq;

/**
 * Created by Administrator on 2017/7/16.
 */
@Component
@Aspect
@Order(3)
@Slf4j
public class JsonAssert {
    public JsonAssert(){}

    @Pointcut("@annotation(jsonAssertions)")
    public void jsonAssertPointcut(JsonAssertions jsonAssertions){}

    @AfterReturning(
            pointcut="jsonAssertPointcut(jsonAssertions)",
            returning="obj"
    )
    public void doHeaderAssert(JsonAssertions jsonAssertions,Object obj){
                if(obj instanceof JSON || obj instanceof HttpResponse){
                    JSON jo=null;
                    if(obj instanceof JSON){
                        jo=(JSON)obj;
                    }else{
                        HttpResponse httpResponse=(HttpResponse) obj;
                        if(httpResponse.getBody() instanceof JSON)
                            jo=(JSON)httpResponse.getBody();
                    }
                    if(jo==null) {
                        return;
                    }

                    JsonRule[] jsonRules=jsonAssertions.value();

                    int len=jsonRules.length;


                    for(int i=0;i<len;i++){
                        JsonRule jsonRule=jsonRules[i];
                        String jsonPath=jsonRule.jsonPath();
                        String expectedValue =jsonRule.expectedVal();
                        Op op=jsonRule.op();

                        Class type=jsonRule.type();
                        Object _expectedValue=expectedValue;
                        if(type == Integer.class){
                            _expectedValue= Integer.parseInt(expectedValue);
                        }else if(type ==Float.class){
                            _expectedValue=Float.parseFloat(expectedValue);
                        }else if(type == Boolean.class){
                            _expectedValue=Boolean.parseBoolean(expectedValue);
                        }
                        if(jsonRule.enabled()){
                            switch (op){
                                case eq:
                                    com.jayway.jsonassert.JsonAssert.with(jo.toString()).assertThat(jsonPath, Matchers.equalTo(_expectedValue),jsonRule.error());
                                    break;
                                case contains:
                                    break;
                                default:
                                    log.info("Srping","verify on http reponse header");
                            }
                        }



                    }
                }
    }
}
