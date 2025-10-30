package com.infosupport.jpademo.dao;

import com.infosupport.jpademo.domain.LeaseCar;
import jakarta.persistence.EntityManagerFactory;

public class LeaseCarDao extends Dao<LeaseCar> {

    public LeaseCarDao(EntityManagerFactory emf) {
        super(emf, LeaseCar.class);
    }
}
