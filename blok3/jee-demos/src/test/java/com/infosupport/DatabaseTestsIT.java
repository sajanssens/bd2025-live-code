package com.infosupport;

import com.infosupport.jeedemo.domain.Beer;
import com.infosupport.jeedemo.domain.BeerRepo;
import com.infosupport.jeedemo.domain.JPAEntity;
import com.infosupport.jeedemo.domain.Repo;
import com.infosupport.jeedemo.domain.User;
import com.infosupport.jeedemo.domain.UserRepo;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.function.BiFunction;

import static com.infosupport.jeedemo.api.util.PasswordUtils.digest;

@Testcontainers
class DatabaseTestsIT implements WithAssertions {

    private static EntityManagerFactory emfTest;

    private BeerRepo beerRepo;
    private UserRepo userRepo;

    @Container
    public static MySQLContainer<?> db =
            new MySQLContainer<>("mysql:8.4.0")
                    .withDatabaseName("beers-db")
                    .withUsername("root")
                    .withPassword("root");

    @BeforeAll
    static void beforeAll() {
        initDatabase();
    }

    @BeforeEach
    void initOneTest() {
        this.beerRepo = new BeerRepo();
        this.userRepo = new UserRepo();
        this.beerRepo.setEm(emfTest.createEntityManager());
        this.userRepo.setEm(emfTest.createEntityManager());
    }

    @Test
    void createAndRead_beer_givesTheSameBeerFromTheDatabase() {
        // arrange
        var b = Beer.builder().brand("Leffe").alc(6.3).build();

        // act
        Beer beerCreated = transactional(this.beerRepo, Repo::create, b);
        Beer beerRead = this.beerRepo.read(beerCreated.getId());

        // assert
        assertThat(beerRead).isNotNull();
        assertThat(beerRead.getBrand()).isEqualTo("Leffe");
        assertThat(beerRead.getAlc()).isEqualTo(6.3);
    }

    @Test
    void createAndFindUserByUsernameAndPassword_givesThatUserFromTheDatabaseWithAnId() {
        // arrange
        String username = "john";
        String password = "doe123!";
        var u = User.hashed(username, password);

        // act
        User created = transactional(this.userRepo, Repo::create, u);
        User read = this.userRepo.findByUsernameAndPassword(username, password);

        // assert
        assertThat(created).isNotNull();
        assertThat(read).isNotNull();
        assertThat(read.getId()).isNotEqualTo(0);
        assertThat(read.getUsername()).isEqualTo(username);
        assertThat(read.getPassword()).isEqualTo(digest(password));
    }

    private <E extends JPAEntity> E transactional(Repo<E> target, BiFunction<Repo<E>, E, E> f, E e) {
        target.getEm().getTransaction().begin();
        E applied = f.apply(target, e);
        target.getEm().getTransaction().commit();
        return applied;
    }

    private static void initDatabase() {
        emfTest = Persistence.createEntityManagerFactory("test-containers");
        System.setProperty("db.port", db.getFirstMappedPort().toString());
        System.setProperty("db.host", db.getHost());
    }
}
