package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyRestApiBaseUrl{
    // setUp metodu ile tekrarlı yapılacak işlemleri topluyoruz.
    // Test öncesi çalışacak şekilde @Before anotasyonu ekliyoruz.
   protected RequestSpecification spec;
    @Before
    public void Setup(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://dummy.restapiexample.com/api/v1")
                .setContentType(ContentType.JSON)
                .build();
    }


    //Her sorguda tekrar eden datalari buraya girecegiz.

    //Accep Type olarak ContentType.JSON ekledik. Bunu her zaman isteyebilir bu nedenle baseUrls'e ekledik
 }
