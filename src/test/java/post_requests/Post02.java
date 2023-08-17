package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like
            {
                "bookingid": 5315,
                "booking": {
                    "firstname": "John",
                    "lastname": "Doe",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-09-09",
                        "checkout": "2021-09-21"
                    }
                }
             }
     */

    @Test
    public void post02() {

        //Set the URL
        spec.pathParams("first", "booking");

        //Set the expected data

        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String, String> bookingDatesData = obj.bookingDateMapper("2021-09-09", "2021-09-21");
        Map<String, Object> expectedData = obj.expectedDataMapper("John", "Doe", 11111, true, bookingDatesData, null);
        System.out.println("expectedData = " + expectedData);


        //Set the request and get the response
        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesData.get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesData.get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));


        /*
        assertEquals(bookingDatesData.get("checkin"),((Map((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
    assertEquals(bookingDatesData.get("checkout"),((Map((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
    Son iki satirda bir kez (Map) yazinca get() metodu gelmedi. Iki kez (Map) yazinca Map metodu olan get()'i kullanabildik.
    actualData.get() icine oncelikle "booking" yazmaliyiz. booking icinde obje var. "firstname"'de string vardi.
    booking sonrasi get() yazinca get kizardi. Map'a cast yapmamiz gerekti. get() gelince icine bookingdates yazdik.
    Body'mizi inceleyince once booking sonra bookingdates ic ice json verileri vardi.
    Ic ice json verilerini her get metodu icine yazdik ve bookingdates icindeki checkin ve checkout datalarina ulastik.
         */
    }


}
