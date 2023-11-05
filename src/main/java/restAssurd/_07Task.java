package restAssurd;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.containsString;

/** Task 1
 * create a request to https://httpstat.us/203
 * expect status 203
 * expect content type TEXT
 */

/** Task 2
 * create a request to https://httpstat.us/203
 * expect status 203
 * expect content type TEXT
 * expect BODY to be equal to "203 Non-Authoritative Information"
 */

/** Task 3
 *  create a request to https://jsonplaceholder.typicode.com/todos/2
 *  expect status 200
 *  expect content type JSON
 *  expect title in response body to be "quis ut nam facilis et officia qui"
 */

/** Task 4
 * create a request to https://jsonplaceholder.typicode.com/todos/2
 *  expect status 200
 *  expect content type JSON
 *  expect response completed status to be false
 */

/** Task 5
 * create a request to https://jsonplaceholder.typicode.com/todos
 * expect status 200
 * expect content type JSON
 * expect third item have:
 *      title = "fugiat veniam minus"
 *      userId = 1
 */

/** Task 6
 * create a request to https://jsonplaceholder.typicode.com/todos/2
 * expect status 200
 * Converting Into POJO
 */

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import restAssurd.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.oneOf;


public class _07Task {
    /** Task 1
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     */
    @Test
    public void task1(){
        get("https://httpstat.us/203")
                .then()
                .statusCode(205)
                .contentType(ContentType.TEXT)
                .log().status();

    }
    @Test
    public void task2(){
        /** Task 2
         * create a request to https://httpstat.us/203
         * expect status 203
         * expect content type TEXT
         * expect BODY to be equal to "203 Non-Authoritative Information"
         */


        get("https://httpstat.us/203")
                .then()
                .log().body()
                .statusCode(203)
                .contentType(ContentType.TEXT)
                .body(containsString("203 Non-Authoritative"));


    }
    @Test
    public void task3(){
        /** Task 3
         *  create a request to https://jsonplaceholder.typicode.com/todos/2
         *  expect status 200
         *  expect content type JSON
         *  expect title in response body to be "quis ut nam facilis et officia qui"
         */

        get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .body("title",equalTo("quis ut nam facilis et officia qui"));
    }
    @Test
    public void task4(){
        /** Task 4
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         *  expect status 200
         *  expect content type JSON
         *  expect response completed status to be false
         */

        get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .statusCode(oneOf(200,201))
                .contentType(ContentType.JSON)
                .body("completed",equalTo(false));

    }
    @Test
    public void task5(){
        /** Task 5
         * create a request to https://jsonplaceholder.typicode.com/todos
         * expect status 200
         * expect content type JSON
         * expect third item have:
         *      title = "fugiat veniam minus"
         *      userId = 1
         */

       get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .statusCode(oneOf(200,201))
                .contentType(ContentType.JSON)
                .log().body()
               .body("title[2]", equalTo("fugiat veniam minus"))
               .body("userId[2]", equalTo(1));


        Response response = get("https://jsonplaceholder.typicode.com/todos");
        List<String> list = response
                .then()
                .statusCode(oneOf(200,201,202))
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                        .getList("title");
        System.out.println(list);
       Assert.assertTrue(list.get(2).contains("fugiat veniam minus"));


        }
    @Test
    public void task6(){
        /** Task 6
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * Converting Into POJO
         */
        ToDo toDo = get("https://jsonplaceholder.typicode.com/todos/2").as(ToDo.class);

        System.out.println("todo.getId()=" + toDo.getId()) ;
        System.out.println("todo.getUserId()=" + toDo.getUserId()) ;
        System.out.println("todo.getTitle()=" + toDo.getTitle()) ;
        System.out.println("todo.getCompleted()=" + toDo.isCompleted()) ;

    }
    @Data
    static class ToDo{
        int userId;
        int id;
        String title;
        boolean completed;

    }
        @Test
        public void task7(){
            /** Task 7
             * create a request to https://jsonplaceholder.typicode.com/todos
             * expect status 200
             * Converting Array Into Array of POJOs
             */

           List<ToDo> todos = get("https://jsonplaceholder.typicode.com/todos")
                    .then()
                    .log().body()
                    .extract()
                    .jsonPath()
                    .getList("",ToDo.class);
            System.out.println(todos.size());
            // comleted true olanlarin title rini ekrana yazdiralim
            int count=0;
            for(ToDo todo : todos){
                if(todo.isCompleted()){
                    count++;
                    System.out.println(count + ". " + todo.getTitle());
                }
            }
            System.out.println("True sayisi : " + count);


        }


    @Test
    public void task8(){

        List<ToDo> todos = get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .extract()
                .jsonPath()
                .getList("findAll{it.completed==true}",ToDo.class);
        System.out.println(todos.size());
        // comleted true olanlarin title rini ekrana yazdiralim



    }

    @Test
    public void task9(){

        List<String> titels = get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .extract()
                .jsonPath()
                .getList("findAll{it.completed==true}.title");
        System.out.println(titels.size());
        // comleted true olanlarin title rini ekrana yazdiralim

    }
    @Test
    public void task10(){
        /** Task 8
         * https://jsonplaceholder.typicode.com/users
         * adresindeki kullanicilari POJO'ya aktariniz
         */

        User user = get("https://jsonplaceholder.typicode.com/users").as(User.class);
        System.out.println(user);

        List<User>users = get("https://jsonplaceholder.typicode.com/users")
                .then()
                .extract()
                .jsonPath()
                .getList("findAll{it.id<5}",User.class);
        System.out.println(users.size());

    }
    @Test
    public void test11(){
        /** Task 9
         * https://jsonplaceholder.typicode.com/users
         * https://jsonplaceholder.typicode.com/albums
         * name'i Ervin Howell olan user'in album isimlerini ve sayisini bulunuz
         */
        int userId = get("https://jsonplaceholder.typicode.com/users")
                .then()
                .extract()
                .jsonPath()
                .getInt("find{it.name=='Ervin Howell'}.id");

        List<String> titles = get("https://jsonplaceholder.typicode.com/users")
                .then()
                .extract()
                .jsonPath()
                .getList("findAll{it.userId==" + userId + "}");
        System.out.println(titles.size());
    }




}



