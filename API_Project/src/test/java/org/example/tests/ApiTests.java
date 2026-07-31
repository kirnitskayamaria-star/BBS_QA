package org.example.tests;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.example.utils.ApiConfig;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class ApiTests extends BaseTest {
    String taskId = "6a6cf3fca687255edc00e6d3";

    @Test
    public void getBalance() {
        given().queryParam("key", ApiConfig.API_KEY)
                .when()
                .get("/user/balance")
                .then()
                .log()
                .all()
                .header("content-type", containsString("application/json"))
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/balance-schema.json"));
    }

    @Test
    public void getBalanceBadRequest400() {
        given().queryParam("invalid_param_name", "123")
                .when()
                .get("/user/balance")
                .then()
                .log()
                .all()
                .statusCode(400) // Ожидаем 400 Bad Request
                .header("content-type", containsString("application/json"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Test
    public void getBalanceBadRequest403() {
        given().queryParam("key", "")
                .when()
                .get("/user/balance")
                .then()
                .log()
                .all()
                .statusCode(403)
                .header("content-type", containsString("application/json"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Test
    public void createTaskSuccess() {
        given().contentType(ContentType.URLENC)
                .queryParam("key", ApiConfig.API_KEY)
                .formParam("keywords[]", List.of("тест 1", "тест 2"))
                .formParam("engine", "google")
                .formParam("device", "desktop")
                .formParam("language", "ru")
                .formParam("regionId", 0)
                .formParam("top", 50)
                .when()
                .post("/task/create")
                .then()
                .log()
                .all()
                .statusCode(200)
                .header("content-type", containsString("application/json"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/task-schema.json"));
    }

    @Test
    public void createTaskBadRequest() {
        given().contentType(ContentType.URLENC)
                .queryParam("key", ApiConfig.API_KEY)
                .formParam("device", "desktop")
                .formParam("language", "ru")
                .formParam("regionId", 0)
                .formParam("top", 50)
                .when()
                .post("/task/create")
                .then()
                .log()
                .all()
                .statusCode(400)
                .header("content-type", containsString("application/json"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Test
    public void getTaskStatus() {
        given().queryParam("key", ApiConfig.API_KEY)
                .when()
                .get("/task/status/" + taskId)
                .then()
                .log()
                .all()
                .header("content-type", containsString("application/json"))
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/task_status-schema.json"));
    }

    @Test
    public void getTaskStatusBadRequest() {
        given().queryParam("key", ApiConfig.API_KEY)
                .when()
                .get("/task/status/")
                .then()
                .log()
                .all()
                .header("content-type", containsString("application/json"))
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }

    @Test
    public void getTaskResultInsufficientFunds() {
        given().queryParam("key", ApiConfig.API_KEY)
                .when()
                .get("/task/result/" + taskId)
                .then()
                .log()
                .all()
                .header("content-type", containsString("application/json"))
                .statusCode(404)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/error-schema.json"));
    }
}
