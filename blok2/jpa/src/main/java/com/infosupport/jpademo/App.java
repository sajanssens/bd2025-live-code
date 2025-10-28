package com.infosupport.jpademo;

import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import static com.infosupport.jpademo.domain.Gender.Man;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");

    void main() {
        // DBUtil.createDatabase();

        Person bram = new Person("Brams", 42, Man);

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(bram);
        transaction.commit();
    }
}
