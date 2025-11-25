package com.infosupport;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class DatabaseTestsIT {

    private EntityManager session;

    @Container
    public static MySQLContainer<?> db =
            new MySQLContainer<>("mysql:latest")
                    .withDatabaseName("beers-db")
                    .withUsername("root")
                    .withPassword("root");

    @BeforeEach
    void init() {
        initDatabaseSession();
    }

    @Test
    void whenPostAndGetAreCalledItWorks() {
        var b = Beer.builder().brand("Leffe").alc(6.3).build();
        session.getTransaction().begin();
        session.merge(b);
        session.getTransaction().commit();
    }

    private void initDatabaseSession() {
        System.setProperty("db.port", db.getFirstMappedPort().toString());
        System.setProperty("db.host", db.getHost());
        session = Persistence.createEntityManagerFactory("test-containers").createEntityManager();
    }
}
