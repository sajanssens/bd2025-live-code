package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class BeerDao {

    @PersistenceContext
    private EntityManager em;

    public List<Beer> findAll() {
        return em.createQuery("select b from Beer b", Beer.class).getResultList();
    }
}
