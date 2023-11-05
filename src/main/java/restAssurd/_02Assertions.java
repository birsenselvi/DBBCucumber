package restAssurd;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class _02Assertions {
    @Test
    public void test01(){
        given()
                .when()
                .get("https://reqres.in/api/users/1")
                .then()
                .log().body()
                .body("data.id", equalTo(1))
                .body("data.first_name", equalTo("George"))
                .body(containsString("contributions towards"))
                ;
    }
    @Test
    public void test02(){
        given()
                .pathParam("page",2)
                .when()
                .get("https://reqres.in/api/users?page={page}")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .body("page",equalTo(2))
                .body("data[0].first_name", equalTo("Michael"))
                .body("$", hasKey("support"))
                //.body("", hasKey("support")) // "" girildiginde bütün Json i alir. $ ile kiyaslandiginda root Json i alir.
                .body(containsString("rachel.howell@reqres.in"))
                .body("data.first_name",hasItems("Michael","Lindsay")) //firstname listesinde isimleri assert etmek

        ;
    }
}
