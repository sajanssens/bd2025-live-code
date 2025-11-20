package com.infosupport.jeedemo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ExternalBeansFactory {

    @Produces
    public Logger createLogger(InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getClass());
    }
}
