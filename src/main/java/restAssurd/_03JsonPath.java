package restAssurd;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.*;
public class _03JsonPath {
@Test
    public void test2(){
    String json = get("https://jsonplaceholder.typicode.com/comments").asString();
    System.out.println(json);
}
    @Test
    public void test3(){
       Response response = get("https://jsonplaceholder.typicode.com/comments");
       response.prettyPrint();
       response.then().statusCode(200);

    }
    @Test
    public void getDistinctEmailExtention(){

    // verilen json liste icersiinden email uzantilarini cekme.

        List<String>list = get("https://jsonplaceholder.typicode.com/comments").jsonPath().getList("email");

        Set<String> email = new TreeSet<>();
        for (int i = 0; i < list.size(); i++) {
            String[] arr;
            arr = list.get(i).split("[.]");
            String ext = "";
           if(arr.length>0){
                ext = arr[arr.length-1];
           }else{
                ext = list.get(i);

           }

            email.add(ext);
        }
        System.out.println(email);
    }
    @Test
    public void getDistinctEmailExtention2(){

        // verilen json liste icersiinden email uzantilarini cekme.

        String json = get("https://jsonplaceholder.typicode.com/comments").asString();

        List<String> list = JsonPath.from(json).getList("email");
        int sum = 0;

        Set<String> email = new TreeSet<>();
        for (int i = 0; i < list.size(); i++) {
            String[] arr;
            arr = list.get(i).split("[.]");
            String ext = "";
            if(arr.length>0){
                ext = arr[arr.length-1];
                sum++;
            }else{
                ext = list.get(i);

            }

            email.add(ext);
        }
        System.out.println( email);
        System.out.println(sum);
    }
    @Test
    public void test4(){
        Response response = get("https://reqres.in/api/users/1");
        response
                .then()
                .log().body()
                .body("[0].id", equalTo(1))
                .extract()
                .jsonPath()
                .getString("data.email");

    }
    @Test
    public void jsonPath(){
        Response response = get("https://reqres.in/api/users/1");
        String email = response
                .then()
                .log().body()
                .extract()
                .jsonPath()
                .getString("data.email");
        System.out.println("Email = " + email);
        int id = response.then().extract().jsonPath().getInt("data.id");
        System.out.println(id+ " : " + email);
        int id1 = response.jsonPath().getInt("data.id");
        System.out.println(id1);

    }
    @Test
    public void jsonPathList(){
        Response response = get("https://reqres.in/api/users");
        List<String> list = response
                .then()
                .log().body()
                .extract()
                .jsonPath()
                .getList("data.email");

        for (String s: list) {
            System.out.println(s);
        }


    }
    @Test
    public void jsonFindAll(){
        Response response = get("https://reqres.in/api/users");
            List<String> list1 = response
                    .then()
                    .log().body()
                    .extract()
                    .jsonPath()
                    .getList("data.findAll{it.id>5}.email"); // it:aktif obje
            for(String s1:list1) {
             System.out.println(s1);


                List<String> list2 =
             response.jsonPath().getList("data.findAll{it.first_name == 'Charles'}.email");

             for(String s2 : list2){
                 System.out.println(s2);
             }


             String chrles =
                        response.jsonPath().get("data.find{it.first_name == 'Charles'}.email");
                System.out.println(chrles);
                System.out.println("*******************************");

                Map<String, ?> maps = response.jsonPath().getMap("data.find{it.first_name == 'Charles'}");
                System.out.println(maps);
                maps.forEach((k,v)-> System.out.println(k+" : "+v));
            }

    }

    @Test
    public void test_JsonMAp(){

        Response response = get("https://reqres.in/api/users");

        String chrles =
                response.jsonPath().get("data").toString();
        System.out.println(chrles);

        List<Map<String, ?> >maps = response.jsonPath().get("data");
        for (Map<String, ?> map:maps) {
            map.forEach((k,v)-> System.out.println(k+ " : "+ v));

        }
    }
    }



