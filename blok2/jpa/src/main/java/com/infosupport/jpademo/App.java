package com.infosupport.jpademo;

import com.infosupport.jpademo.dao.Dao;
import com.infosupport.jpademo.dao.DepartmentDao;
import com.infosupport.jpademo.dao.LeaseCarDao;
import com.infosupport.jpademo.dao.PersonDao;
import com.infosupport.jpademo.domain.Company;
import com.infosupport.jpademo.domain.Department;
import com.infosupport.jpademo.domain.LeaseCar;
import com.infosupport.jpademo.domain.Person;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.infosupport.jpademo.domain.Gender.Man;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");

    void main() {
        var personDao = new PersonDao(emf);
        var leaseCarDao = new LeaseCarDao(emf);
        var companyDao = new Dao<>(emf, Company.class);
        var departmentDao = new DepartmentDao(emf);

        var belastingdienst = companyDao.create(Company.builder().name("Belastingdienst").build());
        var iv = Department.builder().name("IV").build();
        var janssens = Person.builder().name("Janssens").age(42).gender(Man).build();
        var janssens2 = Person.builder().name("Janssens2").age(42).gender(Man).build();
        var janssens3 = Person.builder().name("Janssens3").age(42).gender(Man).build();
        var car = LeaseCar.builder().brand("Dikke BMW").owner(janssens).build();

        // one-to-one with cascade: -----------------------
        log.debug("Saving car and cascade person.");
        leaseCarDao.create(car);

        // refresh janssens to get the newly created id
        janssens = personDao.update(janssens);

        // one to many - many to one (bidi): ---------------
        iv = departmentDao.create(iv);
        iv.addWorker(janssens);
        janssens = personDao.create(janssens); // beste manier: koppeling opslaan via de owner

        iv.addWorker(janssens2);
        iv.addWorker(janssens3);
        iv = departmentDao.update(iv);// alternatief: koppeling opslaan via de passive side

        // enige voordeel van BIDI:
        Department readIv = departmentDao.read(iv.getId());
        // System.out.println(readIv.getWorkers()); // DOESN'T WORK! LAZY.
        // ... maar dit werkt niet...
        // Use query instead:
        //   JOIN FETCH for collection valued association
        Department ivWithWorkers = departmentDao.findWithWorkers(readIv.getId());
        log.info("Workers of Department IV: ");
        ivWithWorkers.getWorkers().forEach(w -> log.info(w.toString()));

        //   JOIN FETCH for single valued association
        // eerste even Person aan de company koppelen:
        Person p = personDao.read(janssens.getId());
        p.setCompany(belastingdienst);
        personDao.update(p);
        // dan alle medewerkers van die company ophalen:
        List<Person> workersOfBelastingdienst = personDao.findBy(belastingdienst.getId());
        log.info("Workers of Company Belastingdienst: ");
        workersOfBelastingdienst.forEach(w -> log.info(w.toString()));
    }
}

