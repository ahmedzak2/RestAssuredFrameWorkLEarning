package example;

import files.CommonMethods;
import files.PayLoadFirstSection;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
// To import all static package of rest assuerd
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basic {
    public static void main(String[] args) {
        RestAssured.baseURI= "https://rahulshettyacademy.com";
      String response=  given().queryParam("Key","qacklick123").header("Content-Type","application/json")
                .body(PayLoadFirstSection.addPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
       String placeId=  js.get("place_id");
         System.out.println("placeId"+placeId);
         String newAddress ="70 el Byton new ban ma ";

        given().log().all().queryParam("Key","qacklick123").header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();


        String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = CommonMethods.rawToJson(getPlaceResponse);
        //JsonPath js1=new JsonPath(getPlaceResponse); //for parsing Json
        String actualAddress =js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, "70 el Byton new ban ma ");


        String getMockPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();


    }
}