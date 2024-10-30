package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
class NumberResourceTest {

    @Test
    void test_NumberResource() {
        given()
          .when().get("/api/numbers")
          .then()
             .statusCode(200)
             .body("ISBN_13", startsWith("13-"))
             .body("ISBN_10", startsWith("10-"))
             .body(not(hasKey("generationDate")));
    }
}