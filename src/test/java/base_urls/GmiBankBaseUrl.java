package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationHerokuApp.generateToken;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void Setup() {

        spec = new RequestSpecBuilder()
                .setBaseUri("https:gmibank.com/")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJrX3R3YWluIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjkyOTA1OTc5fQ.aTy0amGUsYGjxWbSF3yTtouQxEyZejHJnW9nv9LRr8ekbL4Az7pWAzLewukwWSosPK_zXYqk-GjicwqqAFqLAg")
                .build();
    }

}
