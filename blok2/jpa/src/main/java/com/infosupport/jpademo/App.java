package com.infosupport.jpademo;

import com.infosupport.jpademo.dao.LeaseCarDao;
import com.infosupport.jpademo.domain.LeaseCar;
import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.infosupport.jpademo.domain.Gender.Man;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");

    void main() {
        // var personDao = new PersonDao(emf);
        var leaseCarDao = new LeaseCarDao(emf);

        var jansen = Person.builder().name("Janssens").age(42).gender(Man).build();
        var car = LeaseCar.builder().brand("Dikke BMW").owner(jansen).build();
        log.debug("Saving person and cascade leasecar.");
        leaseCarDao.create(car);
    }
}
