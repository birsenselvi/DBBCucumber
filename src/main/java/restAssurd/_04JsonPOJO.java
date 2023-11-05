package restAssurd;


import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;

import static io.restassured.RestAssured.get;

public class _04JsonPOJO {
    @Test
    public void test1_JsonToPojo(){
        JsonBody user = get("https://reqres.in/api/users/1").as(JsonBody.class);
        System.out.println(user);
    }

    @Data
    static class JsonBody{
        public UserData data;
        public Support support;
    }

    @Data
    static class UserData{
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;


    }

    @Data
    static class Support {
        public String url;
        public String text;

        @Override
        public String toString() {
            return "\n\nSupport{" +
                    "url='" + url + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
    @Data
    static class Users1{


    }
}
