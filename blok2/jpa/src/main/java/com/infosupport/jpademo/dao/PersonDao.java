package com.infosupport.jpademo.dao;

import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PersonDao {

    EntityManagerFactory emf;

    public PersonDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Person create(Person p) {
        var em = emf.createEntityManager();
        try {
            var transaction = em.getTransaction();
            transaction.begin();
            em.persist(p);
            transaction.commit();
            em.close();
            return p;
        } finally {
            em.close();
        }
    }

    public Person read(int id) {
        var em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        em.close();
        return person;
    }

    public Person update(Person p) {
        var em = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        Person mergedP = em.merge(p);// bram gets merged into the session: managed
        transaction.commit();
        em.close();

        return mergedP;
    }

    public void delete(Person p) {
        var em = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        Person person = em.find(Person.class, p.getId());
        em.remove(person);
        transaction.commit();
        em.close();
    }

    public List<Person> findBy(String lastName) {
        var em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE :eenNaam", Person.class);
        query.setParameter("eenNaam", lastName + "%");
        List<Person> resultList = query.getResultList();
        em.close();

        return resultList;
    }
}
