package restAssurd;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class _01Basics {

    /**
     * Rest assured -> backend testleri
     * BDD mantiginda yazilir
     * given() -> ön kosullar
     * when()-> yapilan islem -- method ( GET, POST, ..)
     * then() -> assertions (response)
     * Java ile backend yapabileceggimiz bir library
     */
    @Test
    public void test01_BasicUsage(){
        given().when().then();
    }
    @Test
    public void test02_getAndLog(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                // .log().body() // log istenilen kismi konsola yazdirir.
                .log().all();

    }
    @Test
    public void test03_statusCode(){
        given().when().get("https://reqres.in/api/users?page=1")
                .then()
                .log().status()
                .statusCode(200)
                .contentType("application/json");
    }
    @Test
    public void test04_responseTime(){
        String url = "https://reqres.in/api/users?page=1";
        get(url)
                .then()
                .statusCode(200);
        long time = get(url)
                .timeIn(TimeUnit.MILLISECONDS);
        System.out.println(time);
    }
    @Test
    public void test05_prettyPrint(){
        String url = "https://reqres.in/api/users?page=1";
        Response response = get(url)
                .then()
                .extract()
                .response();
        response.prettyPrint();
        response
                .then()
                .statusCode(200);
    }
    @Test
    public void test06_pathParams(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=1")
                .then()
                .statusCode(200);
        given()
                .pathParam("link", "api")
                .when()
                .get("https://reqres.in/{link}/users?page=1")
                .then()
                .statusCode(200);
        given()
                .pathParam("page", "1")
                .pathParam("link", "api")
                .when()
                .get("https://reqres.in/{link}/users?page={page}")
                .then()
                .statusCode(200)
                .log().status();
    }

    @Test
    public void test07_getSingleUsers(){
        String url = "https://reqres.in/api/users/{num}";
        for (int i = 1; i < 6; i++) {
            given()
                    .pathParam("num", i)
                    .when()
                    .get(url)
                    .then()
                    .log().body();
        }
        // 2. cözüm yolu
        for (int i = 1; i < 6 ; i++) {
            getSingleUser(i);

        }

    }
    public void getSingleUser(int id){
        String url = "https://reqres.in/api/users/{id}";
        given()
                .pathParam("id", id)
                .when()
                .get(url)
                .prettyPrint();
    }
    @Test
    public void test08_BaseUri(){
       RestAssured.baseURI = "https://reqres.in";
       RestAssured.basePath = "/api";
        given()
                .when()
                .get("/users/1")
                .then()
                .log().body();
    }


}
