package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;

import java.util.List;

@RequestScoped @BEER
public class BeerDao implements Dao<Beer> {

    @Inject @Named("dao")
    private Logger log;

    @PersistenceContext // == @Inject(EM) ==
    private EntityManager em;

    public List<Beer> findAll() {
        log.debug("Finding all ....");
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Transactional
    public Beer create(Beer b) {
        return em.merge(b);
    }
}
