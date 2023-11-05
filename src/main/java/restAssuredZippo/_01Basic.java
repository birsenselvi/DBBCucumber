package restAssuredZippo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;

public class _01Basic {
    @Test
    public void getCountry(){

        String url = "https://api.zippopotam.us/";


        given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .log().body();
    }
    @Test
    public void getstatusCode(){

        String url = "https://api.zippopotam.us/";


        given()
                .when()
                .get(url)
                .then()
                .log().status()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }
    @Test
    public void getresponseTime(){

            String url = "https://api.zippopotam.us/";


            get(url)

                    .then()
                    .statusCode(200);
            long time = get(url)
                    .timeIn(TimeUnit.MILLISECONDS);
        System.out.println(time);
    }
    @Test
    public void getprettyPrint(){

        String url = "https://api.zippopotam.us/";


        Response response = get(url)
                .then()
                .extract()
                .response();
        response.prettyPrint();
    }



}
