package example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class responceRequestTest {
    @Test
    public static void testSerializetion() {
        AddPlace place = new AddPlace();

        place.setAccuracy(50);
        place.setLanguage("engilish-EN");
        place.setPhone_number("21121650000");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setAddress("22 smart village ");
        place.setName("ahmed zakaria");
        List<String> mylist = new ArrayList<>();
        Location location = new Location();
        location.setLat(22);
        location.setLng(50);
        mylist.add("male");
        mylist.add("female");
        mylist.add("alien");
        place.setTypes(mylist);
        place.setLocation(location);
        // TODO Auto-generated method stub
        RestAssured.baseURI = "https://rahulshettyacademy.com";
      RequestSpecification requestSpecBuilder=   new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
              .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
RequestSpecification req =  given().spec(requestSpecBuilder).log().all().body(place);
        ResponseSpecification responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        Response res=req
                .when().post("/maps/api/place/add/json").
                then().spec(responseSpecification).extract().response();
        System.out.println(res);

    }
}