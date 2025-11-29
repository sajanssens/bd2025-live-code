package com.infosupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infosupport.jeedemo.domain.Beer;
import com.infosupport.jeedemo.domain.TokenDto;
import com.infosupport.jeedemo.domain.User;
import com.infosupport.jeedemo.domain.UserDto;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;

import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicroShedTest
public class AppIT {

    public static final Network NETWORK = Network.newNetwork();
    public static final String DB_NAME = "beers-db";
    public static final String DB_HOST = "beers-service-db";
    public static final int DB_PORT = 3306;

    @Container
    public static MySQLContainer<?> beersServiceDb = new MySQLContainer<>("mysql:8.4.0")
            .withDatabaseName(DB_NAME)
            .withUsername("root")
            .withPassword("root")
            .withNetwork(NETWORK)
            .withNetworkAliases(DB_HOST)
            .withStartupTimeout(Duration.ofMinutes(1));

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

    @Test
    void whenPostAndGetAreCalledItWorks() {
        var u = User.builder().username("admin").password("admin").role("ADMIN").build();

        given().contentType("application/json")
                .body(gson.toJson(u))
                .when().post("/api/users")
                .then().statusCode(200)
                .extract().as(User.class, ObjectMapperType.GSON);

        var user = given()
                .contentType("application/json")
                .body(gson.toJson(new UserDto("admin", "admin")))
                .when().post("/api/users/login")
                .then().statusCode(200)
                .extract().as(TokenDto.class, ObjectMapperType.GSON);

        String token = user.token();

        var b = Beer.builder().brand("Leffe").alc(6.3).build();

        var beer = given().contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(gson.toJson(b))
                .when().post("/api/beers")
                .then().statusCode(200)
                .extract().response();

        var beers = given().contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when().get("/api/beers")
                .then().statusCode(200)
                .extract().response();

        assertNotNull(beers.getBody());
        assertTrue(beer.asPrettyString().contains("id"));
        assertTrue(beers.asPrettyString().contains("brand"));
        assertTrue(beers.asPrettyString().contains("alc"));
    }
}
