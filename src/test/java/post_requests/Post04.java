package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;


import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post04 extends HerokuAppBaseUrl {
    /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    @Test
    public void post04() {
        // Set the Url
        spec.pathParam("first", "booking");

        // Send the Expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 999, true, bookingDates, "Breakfast");


        // Send the Request and Get the Response
        Response response = given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        // Do Assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println(actualData);
        assertEquals(200, response.statusCode());

        //En buyuk Pojoyu (BookingResponsePojo) actual kismi icin olusturduk. Gelen datada "booking" isimli bir bolum daha oldugu icin orada kullandik

        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        //response as methodu sadece json ve java datalari arasinda donusum yapmaz.
        //jackson-databind ve jackson.datatype dependency leri ile birlikte response as() methodu Integer variable i String'e, String'i Local Date'e gibi farkli classlari birbirine cevirebilmemize imkan tanir

    }
}
/*
response'da kac scope varsa o kadar pojo olmali. Burada 3 tane vardi. BookingResponsePojo'yu istedigimiz sirada olusturabiliriz. Ancak ona expected data kisminda yer yok. En buyuk olan hepsini kapsayan pojoyu
    //Do assertion icine yaziyoruz.
 */