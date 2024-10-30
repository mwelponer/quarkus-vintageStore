package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class BookResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
            .formParam("title", "t")
            .formParam("author", "a")
            .formParam("year", "2024")
            .formParam("genre", "IT")
        .when()
            .post("/api/books")
        .then()
            .statusCode(201)
            .body("ISBN_13", startsWith("13-"))
            .body("title", is("t"))
            .body("author", is("a"))
            .body("year_of_publication", is(2024))
            .body("genre", is("IT"))
            .body("creation_date", startsWith("20"));
    }

}