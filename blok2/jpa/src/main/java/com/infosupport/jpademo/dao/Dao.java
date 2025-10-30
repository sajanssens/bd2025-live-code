package com.infosupport.jpademo.dao;

import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Dao<T extends HasId> {

    private final Class<T> type;
    protected final EntityManagerFactory emf;

    public Dao(EntityManagerFactory emf, Class<T> type) {
        this.emf = emf;
        this.type = type;
    }

    public T create(T p) {
        return update(p);
    }

    public T read(long id) {
        var em = emf.createEntityManager();
        T t = em.find(type, id);
        em.close();
        return t;
    }

    public T update(T p) {
        var em = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        T mergedP = em.merge(p);// bram gets merged into the session: managed
        transaction.commit();
        em.close();

        return mergedP;
    }

    public void delete(T entity) {
        var em = emf.createEntityManager();
        var transaction = em.getTransaction();
        transaction.begin();
        T foundEntity = em.find(type, entity.getId());
        em.remove(foundEntity);
        transaction.commit();
        em.close();
    }

    public List<T> findAll() {
        var em = emf.createEntityManager();
        List<T> resultList = em.createQuery("SELECT p FROM " + type.getSimpleName(), type).getResultList();
        em.close();

        return resultList;
    }
}
