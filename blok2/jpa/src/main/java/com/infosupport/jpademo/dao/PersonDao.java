package com.infosupport.jpademo.dao;

import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PersonDao extends Dao<Person> {

    public PersonDao(EntityManagerFactory emf) {
        super(emf, Person.class);
    }

    public List<Person> findBy(String lastName) {
        var em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE :eenNaam", Person.class);
        query.setParameter("eenNaam", lastName + "%");
        List<Person> resultList = query.getResultList();
        em.close();

        return resultList;
    }

    public List<Person> findBy(long companyId) {
        var em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("""
                SELECT p 
                FROM Person p 
                JOIN FETCH p.company c 
                JOIN FETCH p.worksAt w 
                WHERE c.id = :cId""", Person.class);
        query.setParameter("cId", companyId);
        List<Person> resultList = query.getResultList();
        em.close();

        return resultList;
    }
}
