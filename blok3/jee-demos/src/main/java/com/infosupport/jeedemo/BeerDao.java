package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class BeerDao {

    @PersistenceContext
    private EntityManager em;

    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }

    @Transactional
    public Beer create(Beer b) {
        return em.merge(b);
    }
}
