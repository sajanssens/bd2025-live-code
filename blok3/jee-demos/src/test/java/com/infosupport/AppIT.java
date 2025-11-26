package com.infosupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infosupport.jeedemo.domain.Beer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class AppIT {
    private static final Network network = Network.newNetwork();
    private static final String dbName = "beers-db";
    private static final String dbHost = "beers-service-db";
    private static final int dbPort = 3306;

    @Container
    public static MySQLContainer<?> beersServiceDb = new MySQLContainer<>("mysql:8.4.0")
            .withDatabaseName(dbName)
            .withUsername("root")
            .withPassword("root")
            .withNetwork(network)
            .withNetworkAliases(dbHost)
            .withStartupTimeout(Duration.ofMinutes(1));

    // create docker container based on the Dockerfile
    @Container
    public static ApplicationContainer beersService = new ApplicationContainer()
            .withReadinessPath("/health/ready")
            // .withExposedPorts(9080)
            .withEnv("MYSQL_HOSTNAME", dbHost)
            // .withEnv("MYSQL_PORT", String.valueOf(dbPort))
            .withEnv("MYSQL_DATABASE", dbName)
            .withEnv("MYSQL_ROOT_PASSWORD", "root")
            .withEnv("MYSQL_USE_SSL", "false")
            .dependsOn(beersServiceDb)
            .withNetwork(network)
            // .withAppContextRoot("/")
            .withStartupTimeout(Duration.ofMinutes(1));

    private static final Gson gson = new GsonBuilder().create();

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = beersService.getBaseURL();
    }

    @Test
    void whenPostAndGetAreCalledItWorks() {
        var b = Beer.builder().brand("Leffe").alc(6.3).build();

        var beer = given().contentType("application/json")
                // .header("Authorization", "Bearer ....")
                .body(gson.toJson(b))
                .when().post("/api/beers")
                .then().statusCode(200)
                .extract().response();

        var beers = given().contentType("application/json")
                .when().get("/api/beers")
                .then().statusCode(200)
                .extract().response();

        assertNotNull(beers.getBody());
        assertTrue(beer.asPrettyString().contains("id"));
        assertTrue(beers.asPrettyString().contains("brand"));
        assertTrue(beers.asPrettyString().contains("alc"));
    }
}
