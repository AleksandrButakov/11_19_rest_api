package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static listener.CustomAllureListener.withCustomTemplates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import models.Credentials;
import models.GenerateTokenResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookstoreTests {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    void getBooksTest() {
        get("/BookStore/v1/Books")
                .then()
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("books", hasSize(greaterThan(0)));
    }

    // выводим все логи
    @Test
    void getBooksWithAllLogsTest() {
        given()
                .log().all()    // логирование запроса
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()    // логирование ответа
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("books", hasSize(greaterThan(0)));
    }

    // выводим логи выборочно
    @Test
    void getBooksWithSomeLogsTest() {
        given()
                .log().uri()    // логирование URI
                .log().method()
                .log().body()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .log().status()    // логирование ответа в консоли
                .log().body()
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("books", hasSize(greaterThan(0)));
    }

    // проверяем получение токена с выборочным выводом логов
    @Test
    void generateTokenTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()    // логирование URI
                .log().method()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()    // логирование ответа
                .log().body()
                .statusCode(200)
                // далее идут проверки
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    // добавляем allure listener
    @Test
    void generateTokenWithAllureListenerTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";
        // фильтр RestAssured можно перенести в @BeforeAll и он будет там вообще все перехватывать
        // RestAssured.filters(new AllureRestAssured()); move to @BeforeAll логирование в Allure
        given()
                .filter(new AllureRestAssured())    // можем так индивидуально к запросу фильтр прописать
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()    // логирование URI
                .log().method()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()    // логирование ответа
                .log().body()
                .statusCode(200)
                // далее идут проверки
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    // добавляем allure listener
    @Test
    void generateTokenWithCustomAllureListenerTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";
        // фильтр RestAssured можно перенести в @BeforeAll и он будет там вообще все перехватывать
        // RestAssured.filters(new AllureRestAssured()); move to @BeforeAll логирование в Allure
        given()
                .filter(withCustomTemplates())    // можем так индивидуально к запросу фильтр прописать
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()    // логирование URI
                .log().method()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()    // логирование ответа
                .log().body()
                .statusCode(200)
                // далее идут проверки
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    // получаем токен
    @Test
    void getTokenTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(data)
                        .log().uri()    // логирование URI
                        .log().method()
                        .log().body()
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .log().status()    // логирование ответа
                        .log().body()
                        .statusCode(200)
                        // далее идут проверки
                        // проверим что вкладка "books" имеет размер больше чем 0
                        .body("status", is("Success"))
                        .body("result", is("User authorized successfully."))
                        .extract().path("token");

        System.out.println("Token: " + token);
    }

    /* добавляем проверку на соответствие JSON схеме, схему вставили в файл:
       resources/schemas/GenerateToken_response_scheme.json
       Схему получили с использованием сайта https://www.jsonschema.net/home,
       отправив запрос который взяли из консоли
     */
    @Test
    void generateTokenJsonSchemeTest() {
        String data = "{ \"userName\": \"alex\", " +
                "\"password\": \"asdsad#frew_DFS2\" }";
        // фильтр RestAssured можно перенести в @BeforeAll и он будет там вообще все перехватывать
        // RestAssured.filters(new AllureRestAssured()); move to @BeforeAll логирование в Allure
        given()
                .filter(withCustomTemplates())    // можем так индивидуально к запросу фильтр прописать
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()    // логирование URI
                .log().method()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()    // логирование ответа
                .log().body()
                .statusCode(200)
                // проверим на соответствие JSON схеме
                .body(matchesJsonSchemaInClasspath("schemas/GenerateToken_response_scheme.json"))
                // далее идут проверки
                // проверим что вкладка "books" имеет размер больше чем 0
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", greaterThan(10));
    }

    @Test
    void generateTokenWithModelTest() {
        // заполняем данные с использованием модели данных
        Credentials credentials = new Credentials();
        credentials.setUserName("alex");
        credentials.setPassword("asdsad#frew_DFS2");

        GenerateTokenResponse tokenResponse =
                given()
                        .filter(withCustomTemplates())    // можем так индивидуально к запросу фильтр прописать
                        .contentType(ContentType.JSON)
                        .body(credentials)
                        .log().uri()    // логирование URI
                        .log().method()
                        .log().body()
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .log().status()    // логирование ответа
                        .log().body()
                        .statusCode(200)
                        // проверим на соответствие JSON схеме
                        .body(matchesJsonSchemaInClasspath("schemas/GenerateToken_response_scheme.json"))
                        .extract().as(GenerateTokenResponse.class);

        assertThat(tokenResponse.getStatus()).isEqualTo("Success");
        assertThat(tokenResponse.getResult()).isEqualTo("User authorized successfully.");
        // длина не меньше 10 символов
        assertThat(tokenResponse.getExpires()).hasSizeGreaterThan(10);
        // длина не меньше 10 символов и начинается с символов eyJ
        assertThat(tokenResponse.getToken()).hasSizeGreaterThan(10).startsWith("eyJ");

    }

}
