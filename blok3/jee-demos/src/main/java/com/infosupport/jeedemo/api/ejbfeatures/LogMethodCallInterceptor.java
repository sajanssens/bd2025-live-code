package com.infosupport.jeedemo.api.ejbfeatures;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Must also be declared in beans.xml!
@LogMethodCall
@Interceptor // AOP = Aspect Oriented Programming
public class LogMethodCallInterceptor {

    private final Logger log = LoggerFactory.getLogger(LogMethodCallInterceptor.class);

    @AroundInvoke
    public Object log(final InvocationContext ic) throws Exception {
        log.debug("Invoking method: {}", ic.getMethod());
        Object proceed = ic.proceed();
        log.debug("Method ({}) invoked successfully", ic.getMethod());

        return proceed;
    }
}
