package com.infosupport.jeedemo.domain;

import com.infosupport.jeedemo.api.ejbfeatures.LogMethodCall;
import com.infosupport.jeedemo.api.ejbfeatures.MeasureMethodDuration;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

@Singleton
@Lock(LockType.READ)
public class BeerRepoEJB {

    private final Logger log = LoggerFactory.getLogger(BeerRepoEJB.class);

    // not thread safe! shared beer...
    private final Beer b = Beer.builder().brand("Shared").alc(5.0).build();

    @PersistenceContext // == @Inject(EM) ==
    private EntityManager em;

    @PostConstruct
    public void postInit() {
        // ....
    }

    // @Transactional: not needed in EJB
    @LogMethodCall @MeasureMethodDuration
    public Beer create(Beer b) {
        log.debug("Creating one fine beer with this ejb...");
        return em.merge(b);
    }

    @Lock(LockType.WRITE) // reading/writing a shared resource, so make it thread safe!
    public void processBeer() {
        String brand = b.getBrand();
        brand += "!";
        b.setBrand(brand);

        b.sip();
    }

    @PreDestroy
    public void clean() {
        // clean up code
    }

    @Asynchronous
    public void fireAndForget() {
        log.debug("Getting various stuff from various sources...");
        getFromExternalService();
        getFromExternalService();
        log.debug("Processing various stuff...");
        processing();
        log.debug("done!");
    }

    @Asynchronous
    public Future<Integer> fireAndReturnInTheFuture() {
        log.debug("Getting value from External Service...");
        int result = getFromExternalService();
        log.debug("Got it!");
        return new AsyncResult<>(result);
    }

    private static int getFromExternalService() {
        blocking(1000);
        return 42;
    }

    private static void processing() {
        blocking(2000);
    }

    private static void blocking(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}













