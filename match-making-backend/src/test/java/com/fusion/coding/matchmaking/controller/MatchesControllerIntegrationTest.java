package com.fusion.coding.matchmaking.controller;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.fusion.coding.matchmaking.TestResourcesProvider.matchesRequestJson;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchesControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldHasMatchedResult() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(matchesRequestJson(true, true, true, 0.9, 1.0))
                .log().all()
        .when()
                .post("http://localhost:" + port + "/match-making/matches")
                .prettyPeek()
        .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("matches", hasSize(2))
                .body("matches.compatibilityScore", everyItem(greaterThanOrEqualTo((float) 0.9)));
    }

    @Test
    public void shouldHasMatchedLowerBoundDistance() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(matchesRequestJson(true, false, true, 200, null))
                .log().all()
        .when()
                .post("http://localhost:" + port + "/match-making/matches")
                .prettyPeek()
        .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("matches", hasSize(3))
                .body("matches.city.name", hasItems("Leeds", "Leeds", "Solihull"));
    }

    @Test
    public void shouldHasMatchedUpperBoundDistance() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(matchesRequestJson(true, false, true, null, 350))
                .log().all()
        .when()
                .post("http://localhost:" + port + "/match-making/matches")
                .prettyPeek()
        .then().assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("matches", hasSize(6))
                .body("matches.displayName", hasItems("Marta", "Katie", "Tracy", "Elizabeth", "Laura", "Katlin"));
    }

    @Test
    public void shouldReturn400() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(matchesRequestJson(true, true, true, null, 1.0))
                .log().all()
        .when()
                .post("http://localhost:" + port + "/match-making/matches")
                .prettyPeek()
        .then().assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
