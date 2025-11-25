package com.infosupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infosupport.jeedemo.domain.Beer;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jupiter.MicroShedTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicroShedTest
@SharedContainerConfig(AppDeploymentConfig.class)
class AppIT {
    private static final Gson gson = new GsonBuilder().create();

    @Test
    void whenPostAndGetAreCalledItWorks() {
        var b = Beer.builder().brand("Leffe").alc(6.3).build();

        var beer = given().contentType("application/json")
                // .header("Authorization", "Bearer ....")
                .body(gson.toJson(b))
                .when().post("/api/beers")
                .then().statusCode(200)
                .extract().response();

        var beers =
                given().contentType("application/json")
                        .when().get("/api/beers")
                        .then().statusCode(200)
                        .extract().response();

        assertNotNull(beers.getBody());
        assertTrue(beer.asPrettyString().contains("id"));
        assertTrue(beers.asPrettyString().contains("brand"));
        assertTrue(beers.asPrettyString().contains("alc"));
    }
}
