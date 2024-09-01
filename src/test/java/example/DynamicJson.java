package example;

import files.CommonMethods;
import files.PayLoadFirstSection;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test(dataProvider ="BookData")
    public void addBokk(String isbn, String asile ){

        RestAssured.baseURI="https://rahulshettyacademy.com";

             String  respone=   given().header("Content-Type","application/json").body(PayLoadFirstSection.AddBook(isbn,asile)).when().log().all().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = CommonMethods.rawToJson(respone);
       String id = js.getString("ID");
        System.out.println(id);
    }
    @Test()
    public void useJsonFile(String isbn, String asile ) throws IOException {

        RestAssured.baseURI="https://rahulshettyacademy.com";

        RestAssured.baseURI= "https://rahulshettyacademy.com";
        String response=  given().queryParam("Key","qacklick123").header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("D:\\TESTING\\AUtomations\\RestAssured\\src\\test\\resources\\Library+API.postman_collection.json")))).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
        JsonPath js = CommonMethods.rawToJson(response);
        String id = js.getString("ID");
        System.out.println(id);
    }
    // to create function to return array of the data to make you test with more than one combination
    @DataProvider(name = "BookData")
    public Object[][] getData(){

        // array of the two elements which we need to send
       return new Object[][]{{"",""},{"",""},{"",""}};
    }
}
