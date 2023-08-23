package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02_GetBookingById  extends HerokuAppBaseUrl {

      /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            Kullanici GET request gonderir
        Then
            Status Code = 200
        And
            Body:
              {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
}
     */


    @Test
    public void getBookingById() {

        //Set the URL
        spec.pathParams("first","booking","second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingdates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expecteData=new BookingPojo("Jim","Brown",111,true,bookingdates,"Breakfast");


        //Send the request and get the response
        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        //Do assertion
        BookingPojo actuslData=convertJsonToJava(response.asString(),BookingPojo.class);


        assertEquals(200,response.statusCode());
        assertEquals(expecteData.getFirstname(),actuslData.getFirstname());
        assertEquals(expecteData.getLastname(),actuslData.getLastname());
        assertEquals(expecteData.getTotalprice(),actuslData.getTotalprice());
        assertEquals(expecteData.getDepositpaid(),actuslData.getDepositpaid());
        assertEquals(expecteData.getBookingdates().getCheckin(),actuslData.getBookingdates().getCheckin());
        assertEquals(expecteData.getBookingdates().getCheckout(),actuslData.getBookingdates().getCheckout());
        assertEquals(expecteData.getAdditionalneeds(),actuslData.getAdditionalneeds());


    }
}
