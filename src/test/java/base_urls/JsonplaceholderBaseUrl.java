package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {
    // setUp metodu ile tekrarlı yapılacak işlemleri topluyoruz.
    // Test öncesi çalışacak şekilde @Before anotasyonu ekliyoruz.
   protected RequestSpecification spec;
    @Before
    public void Setup(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .build();
    }


    //Her sorguda tekrar eden datalari buraya girecegiz.

    //Accep Type olarak ContentType.JSON ekledik. Bunu her zaman isteyebilir bu nedenle baseUrls'e ekledik
 }
