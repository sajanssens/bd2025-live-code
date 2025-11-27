package com.infosupport.jeedemo.api.ejbfeatures;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton @Startup
public class ProgammaticTimer {

    @Resource
    private TimerService timerService;

    private final Logger log = LoggerFactory.getLogger(ProgammaticTimer.class);

    private Timer timer;

    @PostConstruct
    public void createTimer() {
        int secondsFromDatabase = getSecondsFromDatabase();
        log.info("Setting a programmatic timeout for {} seconds from now.", secondsFromDatabase);
        this.timer = timerService.createIntervalTimer(0, secondsFromDatabase * 1000L, new TimerConfig("Non persistent", false));
    }

    @Timeout
    public void timeoutExpired() {
        log.debug("Timeout! Next timeout at {}", timer.getNextTimeout());
    }

    private int getSecondsFromDatabase() {
        // go to db and fetch.... (fake)
        return 2;
    }
}
