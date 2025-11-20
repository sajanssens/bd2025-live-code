package com.infosupport.jeedemo.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.util.Collection;

public abstract class Repo<E extends JPAEntity> {

    @PersistenceContext(name = "beers") // Container managed persistence context
    protected EntityManager em;

    @Transactional
    public E create(E c) {
        return em.merge(c);
    }

    public E read(Integer id) { return em.find(E(), id); }

    @Transactional
    public E update(Integer id, E e) {
        E found = em.find(E(), id);
        if (found == null) throw new BadRequestException("Entity with id " + id + " not found.");

        e.setId(id);
        return em.merge(e);
    }

    @Transactional
    public boolean delete(String id) {
        E e = em.find(E(), id);
        if (e == null) return false;

        em.remove(e);
        return true;
    }

    public Collection<E> findAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public Collection<E> search(String q) {
        TypedQuery<E> namedQuery = em.createNamedQuery(typeSimple() + ".search", E());
        namedQuery.setParameter("q", "%" + q + "%");
        return namedQuery.getResultList();
    }

    private String typeSimple() { return E().getSimpleName(); }

    public abstract Class<E> E();
}
