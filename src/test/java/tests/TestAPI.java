package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.lombok.Homework;
import models.lombok.HomeworkResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static listener.CustomAllureListener.withCustomTemplates;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAPI {
    /*
        curl -X 'POST' \
          'https://petstore.swagger.io/v2/store/order' \
          -H 'accept: application/json' \
          -H 'Content-Type: application/json' \
          -d '{
          "id": 0,
          "petId": 0,
          "quantity": 0,
          "shipDate": "2022-04-24T12:46:04.571Z",
          "status": "placed",
          "complete": true
        }'
     */

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://petstore.swagger.io";
    }

    Response response;

    @Test
    @DisplayName("Swagger Petstore POST test")
    void swaggerPetstorePostTest() {
        /* String data = "{ \"id\": 0, " +
                "\"petId\": 0, " +
                "\"quantity\": 0, " +
                "\"shipDate\": \"2022-04-24T12:46:04.571Z\", " +
                "\"status\": \"placed\", " +
                "\"complete\": true }";
         */

        Homework credentials = new Homework();
        credentials.setId(0);
        credentials.setPetId(0);
        credentials.setQuantity(0);
        credentials.setShipDate("2022-04-24T12:46:04.571Z");
        credentials.setStatus("placed");
        credentials.setComplete(true);

        HomeworkResponse homeworkResponse =
                given()
                        // .filter(new AllureRestAssured())
                        .filter(withCustomTemplates())
                        .contentType(ContentType.JSON)
                        .body(credentials)
                        // .log().all()
                        .log().method()
                        .log().uri()
                        .log().headers()
                        .log().body()
                        .when()
                        .post("/v2/store/order")
                        .then()
                        // .log().all()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schemas/Generate_response_scheme.json"))
                        //.body("status", is("placed"));
                        .extract().as(HomeworkResponse.class);

        /*
            "id": 9222968140497185134,
            "petId": 0,
            "quantity": 0,
            "shipDate": "2022-04-24T12:46:04.571+0000",
            "status": "placed",
            "complete": true
        */
        assertThat(homeworkResponse.getId()).isNotZero();
        assertThat(homeworkResponse.getShipDate()).hasSizeGreaterThan(12);
        assertThat(homeworkResponse.getStatus()).isEqualTo("placed");
    }

}