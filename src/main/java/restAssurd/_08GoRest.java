package restAssurd;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.oneOf;

public class _08GoRest {
    String accessToken = "d837b3566f7c6de25d7d6d5039e055d34943419d5f77fb29ead52bf42d495061";

    /***
     * List users
     * curl -i
     * -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer ACCESS-TOKEN"
     * -XGET "https://gorest.co.in/public/v2/users"
     */
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = "";
    }
    @Test
    public void test1(){
        given()
                .headers("Authorization", "Bearer " + accessToken)
                .accept("application/json")
                .when()
                .get("https://gorest.co.in/public/v2/users")
                .then()
                .log().body()
                .statusCode(oneOf(200,201))
                .contentType(ContentType.JSON)
                .body(not(empty()));
    }


    /**
     * Create user
     * curl -i
     * -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer ACCESS-TOKEN"
     * -XPOST "https://gorest.co.in/public/v2/users"
     * -d '{"name":"Tenali Ramakrishna", "gender":"male",
     * "email":"tenali.ramakrishna@15ce.com", "status":"active"}'
     */
    /**
     * Update user
     * curl -i
     * -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer ACCESS-TOKEN"
     * -XPATCH "https://gorest.co.in/public/v2/users/628347"
     * -d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com",
     * "status":"active"}'
     */
    /**
     * Delete user
     * curl -i
     * -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer ACCESS-TOKEN"
     * -XDELETE "https://gorest.co.in/public/v2/users/628347"
     */

}
