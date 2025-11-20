package com.infosupport.jeedemo.domain;

import com.infosupport.jeedemo.BEER;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;

import java.util.List;

@ApplicationScoped @BEER
public class BeerRepo extends Repo<Beer> {

    @Inject Logger log;

    @PersistenceContext // == @Inject(EM) ==
    private EntityManager em;

    public List<Beer> findAll() {
        log.debug("Finding all ....");
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Override
    public Class<Beer> E() { return Beer.class; }
}
