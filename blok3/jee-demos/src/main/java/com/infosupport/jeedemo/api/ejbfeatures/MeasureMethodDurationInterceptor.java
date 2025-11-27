package com.infosupport.jeedemo.api.ejbfeatures;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.NANOS;

// Must also be declared in beans.xml!
@MeasureMethodDuration
@Interceptor // AOP = Aspect Oriented Programming
@Priority(Interceptor.Priority.APPLICATION)
public class MeasureMethodDurationInterceptor {

    private final Logger log = LoggerFactory.getLogger(MeasureMethodDurationInterceptor.class);

    @AroundInvoke
    public Object measureDuration(InvocationContext targetMethod) throws Exception {
        var startTime = LocalDateTime.now();
        try {
            return targetMethod.proceed(); // like chain.next
        } finally {
            var endTime = LocalDateTime.now();
            long duration = NANOS.between(startTime, endTime);
            log.debug("{} {} method duration was {}", targetMethod.getClass().getSimpleName(), targetMethod.getMethod().getName(), duration);
        }
    }
}
