package com.infosupport.jeedemo.api.ejbfeatures;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton @Startup
public class ScheduledTimer {

    private final Logger log = LoggerFactory.getLogger(ScheduledTimer.class);

    @PostConstruct
    public void init() {
        log.info("Setting auto timeout for 3 seconds from now.");
    }

    @Schedule(hour = "*", minute = "*", second = "*/3", persistent = false)
    public void drink() {
        log.debug("Sip...");
    }
}
