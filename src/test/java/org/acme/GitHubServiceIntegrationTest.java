package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class GitHubServiceIntegrationTest {

    @Test
    public void testFetchUserRepositories_HappyPath() {
        given()
                .when().get("/repositories/octocat")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty())) // Sprawdzamy, czy lista nie jest pusta
                .body("[0].repositoryName", notNullValue()) // Pierwszy element ma nazwę repozytorium
                .body("[0].ownerLogin", is("octocat")) // Sprawdzamy właściciela repozytorium
                .body("[0].branches", not(empty())) // Sprawdzamy, czy są branche
                .body("[0].branches[0].name", notNullValue()) // Pierwszy branch ma nazwę
                .body("[0].branches[0].lastCommitSha", notNullValue()); // SHA ostatniego commita istnieje
    }
}