package com.infosupport.jeedemo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ExternalBeansProducer {

    @Produces
    public Logger createSlf4jLogger(InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getClass().getName());
    }

    @Produces
    public java.util.logging.Logger createJavaUtilLoggingLogger(InjectionPoint injectionPoint) {
        return java.util.logging.Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
