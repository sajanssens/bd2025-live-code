package com.infosupport.jpademo;

import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.infosupport.jpademo.domain.Gender.Man;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");

    void main() {
        // Logging demo: ---------------
        log.error("Dit is een fout!");
        log.warn("Waarschuwing...");
        log.info("Hier wat informatie over ..."); // jip en janneke logging voor de helpdesk
        log.debug("DEBUG"); // technische logging voor developers
        log.trace("TRACE"); // technische detail logging voor developers

        // DBUtil.createDatabase();

        Person bram = new Person("Janssens", 42, Man); // new

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(bram); // bram is a managed entity
        bram.setName("Janssenss");
        transaction.commit();
        em.close();

        // bram is now detached

        em = emf.createEntityManager();
        transaction = em.getTransaction();
        transaction.begin();
        em.merge(bram); // bram gets merged into the session: managed
        transaction.commit();

        transaction.begin();
        bram.setName("Janssens"); // bram is still managed
        transaction.commit();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE :eenNaam", Person.class);
        query.setParameter("eenNaam", "Janssen%");
        List<Person> resultList = query.getResultList();
        System.out.println(resultList);

        transaction.begin();
        System.out.println(em.contains(bram));
        Person person = em.find(Person.class, bram.getId());
        em.remove(person);
        transaction.commit();
    }
}
