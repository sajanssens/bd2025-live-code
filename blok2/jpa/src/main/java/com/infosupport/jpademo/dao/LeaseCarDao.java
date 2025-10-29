package com.infosupport.jpademo.dao;

import com.infosupport.jpademo.domain.LeaseCar;
import jakarta.persistence.EntityManagerFactory;

public class LeaseCarDao {

    EntityManagerFactory emf;

    public LeaseCarDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public LeaseCar create(LeaseCar p) {
        var em = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        em.persist(p);
        transaction.commit();
        em.close();

        return p;
    }
}
