package com.infosupport.jpademo.dao;

import com.infosupport.jpademo.domain.Department;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class DepartmentDao extends Dao<Department> {

    public DepartmentDao(EntityManagerFactory emf) {
        super(emf, Department.class);
    }

    public Department findWithWorkers(long depId) {
        var em = emf.createEntityManager();
        TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d JOIN FETCH d.workers WHERE d.id = :id", Department.class);
        query.setParameter("id", depId);
        Department resultList = query.getSingleResult();
        em.close();

        return resultList;
    }
}
