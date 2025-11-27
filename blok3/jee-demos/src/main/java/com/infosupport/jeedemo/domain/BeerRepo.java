package com.infosupport.jeedemo.domain;

import com.infosupport.jeedemo.domain.qualifiers.BEER;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;

import java.util.List;

@ApplicationScoped @BEER
public class BeerRepo extends Repo<Beer> {

    @Inject Logger log;

    @PostConstruct
    public void postInit() {
        // ....
    }

    public List<Beer> findAll() {
        log.debug("Finding all ....");
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Override
    public Class<Beer> E() { return Beer.class; }
}
