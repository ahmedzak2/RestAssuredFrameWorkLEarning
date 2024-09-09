package example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BugTest {
    @Test
    public static void createBug() {
        RestAssured.baseURI = "https://ahmedtestingrest.atlassian.net/";

        String authHeader = "Basic " + "YWhtZWR6aWtvNTAwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBwRDVyZlNjbEFhYjFha2E5TDZBdTNuaDYzalhNU2tYb3ZQTG9rY3RlRG1wcEFfLWJlcDdrYWwwUV9lRzlFLXZubzBpYTZzMHJiR1c4RGhRRnpjcEQwRXI1RlR2MGJWb2ZCUjVoY0dmMlpHOU80U2Myc1RxRVhaMkduVUsxT2ZJcnV6aUZEdHdwaVBnYzl4R05VVElGd3VPaUh1ZWdFMWl3UHZGMERrTUdPbDA9ODI5MjYyRDA"; // Replace your_encoded_string_here with the output from your PowerShell script

        String response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"The Home page is not opening - error 404\",\n" +
                        "       \"description\": \"Creating of an Home issue using project keys and issue type names using the REST API\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}\n")
                .log().all()
                .post("rest/api/2/issue")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String ID = js.getString("id");
        System.out.println(ID);
        given()
                .header("X-Atlassian-Token", "no-check")
                .header("Authorization", authHeader).log().all()
                .multiPart("file",new File("D:\\TESTING\\AUtomations\\RestAssured\\src\\test\\resources\\ScreenshotA.png")).post("rest/api/2/issue/"+ ID+"/attachments").then().log().all().assertThat().statusCode(200);

        given()
                .header("X-Atlassian-Token", "no-check").pathParams("Key",ID)
                .header("Authorization", authHeader).log().all()
                .multiPart("file",new File("D:\\TESTING\\AUtomations\\RestAssured\\src\\test\\resources\\Screenshot 2024-07-08 070126.png")).post("rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);


    }



}
