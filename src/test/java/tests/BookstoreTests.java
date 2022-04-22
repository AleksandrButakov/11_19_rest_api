package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static listener.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
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

}
