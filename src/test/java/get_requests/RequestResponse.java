package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {
    /*
    NOTLAR:
        1-Manuel testler icin Postman kullanacagiz.
        2-API otomasyon testleri icin REST Assured kutuphanesini kullanacagiz.
        3-Otomasyon kodlarini yazarken su adimlari takip edecegiz:
            a.Gereksinimleri anlama
            b.Test senaryolarini yazma
                i.Test senaryolarini yazarken Gherkin dilini kullanacagiz.
                    -Given:On kosullar:Endpoint, body...
                    -When:Islemler Get, Post, Put, Delete...
                    -Then:Donutler: Assertion, Close...
                    -And:Coklu islemlerin yapilacagi zaman kullanilir.
            c.Otomasyon kodlarini yazarken su adimlari takip ederiz:
               1- Set the URL = URL'i tanımla
               2- Set the expected data = Beklenen dataları ayarla
               3- Send the request and get the response = İsteği gönder ve cevabı al
               4- Do assertion = Doğrulama yap

     */
        /*
        Request ==> istek
        Response ==> istege gonderilen cevap
        Status code = Gelen cevabin basari durumu
        Post ==> creat ==> Request olusturma islemi => Istek olusturma
        Get ==> read ==>Request sorgulama => Istegin olusup olusmadigini veya
        verilen url deki bilgilerin dogrulugunu sorgulama
        Put ==> update ==> Request i update etme => Istegi guncelleme
        Delete ==> delete ==> Request'i delete etme => Istegi silme islemi
        Bu islemlere kisaca CRUD islemleri denir
         */

            //1- Set the URL = URL'i tanımla
            //2- Set the expected data = Beklenen dataları ayarla
            //3- Send the request and get the response = İsteği gönder ve cevabı al
            //4- Do assertion = Doğrulama yap






    public static void main(String[] args) {
        //Get testi nasil yapilir?
        String url = "https://petstore.swagger.io/v2/pet/1903";
        Response response = given().when().get(url);
      //  response.prettyPrint();

        //Statis code nasil yazdirilir?
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("_______________________");

        //Content Type nasil yazdirilir?
        System.out.println("Content Type: " + response.contentType());
        System.out.println("_______________________");

        //Status Line nasil yazdirilir?
        System.out.println("Status Line: " + response.statusLine());
        System.out.println("_______________________");

        //Header bolumundeki bir veri nasil yazdirilir?
        System.out.println("Header | Server: " + response.header("Server"));
        System.out.println("_______________________");
        System.out.println("Header | Connection: " + response.header("Connection"));

        //Headers bolumu nasil yazdirilir?
        System.out.println("Headers: " + response.headers());
        System.out.println("_______________________");

        //Time bilgisi nasil yazdirilir?
        System.out.println("Time: " + response.time());
        System.out.println("_______________________");

    }
}
