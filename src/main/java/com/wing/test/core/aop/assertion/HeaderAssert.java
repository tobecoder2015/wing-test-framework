package com.wing.test.core.aop.assertion;



import com.wing.test.core.annotation.assertion.HeaderAssertions;
import com.wing.test.core.annotation.assertion.HeaderRule;
import com.wing.test.core.annotation.assertion.Op;
import com.wing.test.core.httpsender.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */
@Component
@Aspect
@Order(3)
@Slf4j
public class HeaderAssert {
    public HeaderAssert(){}

    @Pointcut("@annotation(headerAssertions)")
    public void headerAssertPointcut(HeaderAssertions headerAssertions){}

    @AfterReturning(
            pointcut="headerAssertPointcut(headerAssertions)",
            returning="obj"
    )
    public void doHeaderAssert(HeaderAssertions headerAssertions,Object obj){
                if(obj instanceof HttpResponse){
                    HttpResponse httpResponse=(HttpResponse) obj;
                    Map headers=httpResponse.getHeaders();
                    log.info("Srping","verify on http reponse header");
                    HeaderRule[] headerRules=headerAssertions.value();
                    int len=headerRules.length;

                    for(int i=0;i<len;i++){
                        HeaderRule headerRule=headerRules[i];
                        String header=headerRule.header();
                        String value =headerRule.value();
                        Op op=headerRule.op();
                        String error=headerRule.error();
                        String actualValue=headers.containsValue(header)?(String) headers.get(header):null;

                        assert  actualValue !=null :String.format("返回报文的header里没有%s",new Object[]{header});

                        switch (op){
                            case eq:
                                assert  actualValue.equalsIgnoreCase(value):error;
                                break;
                            case contains:
                                assert  actualValue.contains(value):error;
                                break;
                            default:
                                log.info("Srping","verify on http reponse header");
                        }
                    }
                }
    }
}
