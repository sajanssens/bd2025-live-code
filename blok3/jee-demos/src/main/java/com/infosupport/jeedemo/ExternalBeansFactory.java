package com.infosupport.jeedemo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ExternalBeansFactory {

    @Produces @Named("general")
    public Logger createLogger(InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getClass());
    }

    @Produces @Named("dao")
    public Logger createLoggerForDao(InjectionPoint ip) {
        return LoggerFactory.getLogger("Dao = " + ip.getClass());
    }
}
