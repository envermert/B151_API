package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.converJsonToString;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get13_ObjectMapper_Pojo extends JsonplaceholderBaseUrl {
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
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        String body = converJsonToString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedData = convertJsonToJava(body, JsonPlaceHolderPojo.class);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());





    }
}