package restAssuredZippo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class ZippoCountry {
    @Test
    public void getCountry() {

        RestAssured.baseURI = "https://api.zippopotam.us/";

        given()
                .when()
                .get("TR/16600")
                .prettyPrint();

    }



        // Response'dan mahalle isimlerini çekin

        @Test
    public void getCountry1() {

        RestAssured.baseURI = "https://api.zippopotam.us/";

        String cityCode = "16600";

                String endpoint = "TR/" + cityCode;
                Response response = RestAssured.get(endpoint);

            List<String> places = response
                    .jsonPath()
                    .getList("places.'place name'");

            // System.out.println(places.toString());

            for (int i = 0; i < places.size(); i++) {
                System.out.println(places.get(i));
            }

            //
            String country = response
                    .jsonPath().getString("\"country abbreviation\"");
            System.out.println(country);

            }

        }




