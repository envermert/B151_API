package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
     */

    @Test
    public void get09() {
        //Set the URL
        spec.pathParams("first", "booking", "second", 91);

        //Set the expected data
        Map<String, String> bookingdatesData = new HashMap<>();
        bookingdatesData.put("checkin", "2018-01-01");
        bookingdatesData.put("checkout", "2019-01-01");
        System.out.println("Bookingdates Data : " + bookingdatesData);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jane");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesData);
        expectedData.put("additionalneeds", "Extra pillows please");
        System.out.println("Expected Data :" + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(bookingdatesData.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesData.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

//        Object obj = new HashMap<>();
//        ((Map)obj).get("mm");

        /*
        cteki body icin olusturdugumuz map sonrasi dogrulama yaparken veriye dogrudan ulasamadik.
        Buna ulasmak icin type casting yapmamiz gerekti. Oradaki veriler birer object oldugu icin type casting yapmaliydik.
        Gelen data object oldugundan dogrudan actualData.get(“bokingdates”) yazarak verilere ulasamadik.
        Veriler object olunca map’in get() metodunu cagiramadik. Map’e esitlersek, type casting yaparsak verileri alabiliriz.
        ((Map)actualData.get(“bookingdates”)).get(“checkout”) yazabildik,
        get() metodunu bu sekilde sorunsuz olarak kullanabildik ve icteki map verilerine ulastik.
         */

    }

    @Test
    public void get09b() {
        // Set the URL
        spec.pathParams("first", "booking", "second", 687);

        // Set the expected data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String,String> bookingdatesData = obj.bookingDateMapper("2018-01-01", "2019-01-01");
       Map<String,Object> expectedData = obj.expectedDataMapper("Jane","Doe",111,
                                        true,bookingdatesData,"Extra pillows please");

        // Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
        assertEquals(bookingdatesData.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesData.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));



        //2.Yol:
        JsonPath json = response.jsonPath();
        assertEquals(bookingdatesData.get("checkin"),json.getString("bookingdates.checkin"));
        assertEquals(bookingdatesData.get("checkout"),json.getString("bookingdates.checkout"));


    }




}


