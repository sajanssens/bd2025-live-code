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
    private static final Network NETWORK = Network.newNetwork();
    private static final String DB_NAME = "beers-db";
    private static final String DB_HOST = "beers-service-db";
    private static final int DB_PORT = 3306;

    @Container
    public static MySQLContainer<?> beersServiceDb = new MySQLContainer<>("mysql:8.4.0")
            .withDatabaseName(DB_NAME)
            .withUsername("root")
            .withPassword("root")
            .withNetwork(NETWORK)
            .withNetworkAliases(DB_HOST)
            .withStartupTimeout(Duration.ofMinutes(1));

    // create docker container based on the Dockerfile
    @Container
    public static ApplicationContainer beersService = new ApplicationContainer()
            .withAppContextRoot("/jee-demos")
            .withReadinessPath("/health/ready")
            .withExposedPorts(9080)
            .withEnv("MYSQL_HOSTNAME", DB_HOST)
            .withEnv("MYSQL_PORT", String.valueOf(DB_PORT))
            .withEnv("MYSQL_DATABASE", DB_NAME)
            .withEnv("MYSQL_ROOT_PASSWORD", "root")
            .withEnv("MYSQL_USE_SSL", "false")
            .dependsOn(beersServiceDb)
            .withNetwork(NETWORK)
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
                .then().statusCode(201)
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
