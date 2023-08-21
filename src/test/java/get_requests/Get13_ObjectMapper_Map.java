package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13_ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /* 
        Given 
            https://jsonplaceholder.typicode.com/todos/198 
        When 
            I send GET Request to the URL 
        Then 
            Status code is 200 
        And response body is like 
            { 
                "userId": 10, 
                "id": 198, 
                "title": "quis eius est sint explicabo", 
                "completed": true 
            } 
    */

    @Test
    public void get13() {

        //Set the URL
        spec.pathParams("first", "todos","second",198);

        //Set the expected data
        String body="{ \n" +
                " \"userId\": 10, \n" +
                " \"id\": 198, \n" +
                " \"title\": \"quis eius est sint explicabo\", \n" +
                " \"completed\": true \n" +
                "            } ";


        Map<String,Object> expectedData =ObjectMapperUtils.convertJsonToJava(body, HashMap.class);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}