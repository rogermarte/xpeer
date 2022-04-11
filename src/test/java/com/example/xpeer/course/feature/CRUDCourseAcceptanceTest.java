package com.example.xpeer.course.feature;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CRUDCourseAcceptanceTest extends AcceptanceTest {

    @Test
    void should_post_a_course() throws JSONException {
        String uuid = UUID.randomUUID().toString();
        String moduleUuid = UUID.randomUUID().toString();
        String otherModuleUuid = UUID.randomUUID().toString();

        given()
                .header("content-type", "application/json")
                .body("{\n" +
                        "\t\"id\": \"" + uuid + "\",\n" +
                        "\t\"name\": {\n" +
                        "\t\t\"es\": \"Nombre del curso\",\n" +
                        "\t\t\"en\": \"Course's name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"country\": \"Spain\",\n" +
                        "\t\"city\": \"Barcelona\",\n" +
                        "\t\"description\": {\n" +
                        "\t\t\"es\": \"Spanish name\",\n" +
                        "\t\t\"en\": \"English name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"publish_status\": \"DRAFT\",\n" +
                        "\t\"publication_date\": \"2022-04-05\",\n" +
                        "\t\"modules\": [{\n" +
                        "\t\t\t\"module_id\": \"" + moduleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 1\",\n" +
                        "\t\t\t\t\"en\": \"Module 1\",\n" +
                        "\t\t\t\t\"fr\": \"Module 1\"\n" +
                        "\t\t\t}\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"module_id\": \"" + otherModuleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 2\",\n" +
                        "\t\t\t\t\"en\": \"Module 2\",\n" +
                        "\t\t\t\t\"fr\": \"Module 2\"\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t]\n" +
                        "}")
                .post("/api/v1/courses")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());

        Response response = given()
                .header("content-type", "application/json")
                .get("/api/v1/courses/" + uuid)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract().response();

        JSONObject content = new JSONObject(response.getBody().asString());
        assertThat(content.get("id")).isEqualTo(uuid);
        assertThat(content.get("country")).isEqualTo("Spain");
        assertThat(content.get("city")).isEqualTo("Barcelona");

        given()
                .header("content-type", "application/json")
                .body("{\n" +
                        "\t\"id\": \"" + uuid + "\",\n" +
                        "\t\"name\": {\n" +
                        "\t\t\"es\": \"Nombre del curso\",\n" +
                        "\t\t\"en\": \"Course's name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"country\": \"Spain\",\n" +
                        "\t\"city\": \"Castelldefels\",\n" +
                        "\t\"description\": {\n" +
                        "\t\t\"es\": \"Spanish name\",\n" +
                        "\t\t\"en\": \"English name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"publish_status\": \"DRAFT\",\n" +
                        "\t\"publication_date\": \"2022-04-05\",\n" +
                        "\t\"modules\": [{\n" +
                        "\t\t\t\"module_id\": \"" + moduleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 1\",\n" +
                        "\t\t\t\t\"en\": \"Module 1\",\n" +
                        "\t\t\t\t\"fr\": \"Module 1\"\n" +
                        "\t\t\t}\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"module_id\": \"" + otherModuleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 2\",\n" +
                        "\t\t\t\t\"en\": \"Module 2\",\n" +
                        "\t\t\t\t\"fr\": \"Module 2\"\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t]\n" +
                        "}")
                .put("/api/v1/courses")
                .then()
                .statusCode(HttpStatus.OK.value());

        response = given()
                .header("content-type", "application/json")
                .get("/api/v1/courses/" + uuid)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract().response();

        content = new JSONObject(response.getBody().asString());
        assertThat(content.get("id")).isEqualTo(uuid);
        assertThat(content.get("country")).isEqualTo("Spain");
        assertThat(content.get("city")).isEqualTo("Castelldefels");

        uuid = UUID.randomUUID().toString();
        moduleUuid = UUID.randomUUID().toString();
        otherModuleUuid = UUID.randomUUID().toString();

        given()
                .header("content-type", "application/json")
                .body("{\n" +
                        "\t\"id\": \"" + uuid + "\",\n" +
                        "\t\"name\": {\n" +
                        "\t\t\"es\": \"Nombre del curso\",\n" +
                        "\t\t\"en\": \"Course's name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"country\": \"Spain\",\n" +
                        "\t\"city\": \"Barcelona\",\n" +
                        "\t\"description\": {\n" +
                        "\t\t\"es\": \"Spanish name\",\n" +
                        "\t\t\"en\": \"English name\",\n" +
                        "\t\t\"fr\": \"French name\"\n" +
                        "\t},\n" +
                        "\t\"publish_status\": \"DRAFT\",\n" +
                        "\t\"publication_date\": \"2022-04-05\",\n" +
                        "\t\"modules\": [{\n" +
                        "\t\t\t\"module_id\": \"" + moduleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 1\",\n" +
                        "\t\t\t\t\"en\": \"Module 1\",\n" +
                        "\t\t\t\t\"fr\": \"Module 1\"\n" +
                        "\t\t\t}\n" +
                        "\t\t},\n" +
                        "\t\t{\n" +
                        "\t\t\t\"module_id\": \"" + otherModuleUuid + "\",\n" +
                        "\t\t\t\"module_name\": {\n" +
                        "\t\t\t\t\"es\": \"Modulo 2\",\n" +
                        "\t\t\t\t\"en\": \"Module 2\",\n" +
                        "\t\t\t\t\"fr\": \"Module 2\"\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\n" +
                        "\t]\n" +
                        "}")
                .post("/api/v1/courses")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        response = given()
                .header("content-type", "application/json")
                .get("/api/v1/courses/list")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract().response();

        JSONArray contents = new JSONArray(response.getBody().asString());

        assertThat(contents.length()).isEqualTo(2);

        response = given()
                .header("content-type", "application/json")
                .get("/api/v1/courses/search?page=0&size=1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract().response();

        contents = new JSONArray(response.getBody().asString());

        assertThat(contents.length()).isEqualTo(1);

    }
}
