package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {

    /*
    Given  https://jsonplaceholder.typicode.com/todos
    When   I send a GET request to the Url
    And    Accept type is "application/json"
    Then   HTTP Status Code should be 200
    And    Response format should be "application/ison"
    And    200 adet todos olmalı
    And    başlıklarından birisi  "quis eius est sint explicabo" olmalı
    And    userIds ler arasında 2, 7, and 9 bulunmalı

     */

    @Test
    public void Get() {
        //1. Set Base url
        String url = "https://jsonplaceholder.typicode.com/todos"; //--> Tercih edilmeyen yontem
        spec.pathParam("first", "todos"); //burda first yerine baska seyler de yazilabilir ama genel kullanim bu sekildedir. / isaretini de koymadik bu sekilde kullanimda intellij otomatik olarak / koyuyor.

        //Base Url = Her sorguda tekrarlanan eden kisim
        //path Parametresi ="/" dan sonra base url ye eklene kisim

        //2. Set expected data
        //3. Sent reg and get res
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        //given kismi pre condition(on kosul) bolumudur. Burada given'dan sonra on kosullarimizi ekleriz. Body, Content Type vs..
        //pre-condition kismi cok uzamasin diye ayri bir class olusturup (JsonPlaceHolderBaseUrl) tekrarli kisimlari extends ile oradan aldik (spec objesi olusturup)
        //farkli olan kisimlari ise spec.pathParam(); methoduyla (tek parametreli icin Param birden fazla ise Params) aldik

        //4. Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", hasSize(200)
                        ,"title", hasItem("quis eius est sint explicabo"), "userId", hasItems(2, 7, 9));

    }
    //donen datada bize liste gelmisse ozel bir bilgiye ulasmak icin index kullanilir.
    //hasSize()-->Listenin boyutunu sorgular
    //hasItem --> contain() methodu gibi calisir
    //hasItems -->containAll() methodu gibi calisir
}
