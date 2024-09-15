package example;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.List;

import static io.restassured.RestAssured.given;

public class auth {
    @Test
    public static void testAuth(){
   String response=  given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

            .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

            .formParams("grant_type", "client_credentials").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
      String accessToken=  jsonPath.getString("access_token");
        System.out.println("Access "+accessToken);

    GetCourse getCourse =given().queryParam("access_token",accessToken).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());
        System.out.println(getCourse.getCourses().getWebAutomation().get(0).getCourseTitle());
        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());
        List<Api> apiCourses=getCourse.getCourses().getApi();
        for (int i =0 ;i<apiCourses.size();i++){
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println("prices = "+ apiCourses.get(i).getPrice());
                System.out.println(apiCourses.get(i).getCourseTitle());
            }


        }

        List<WebAutomation> webAutomations = getCourse.getCourses().getWebAutomation();
        for (int y =0 ;y<webAutomations.size();y++){
                System.out.println("prices = "+ webAutomations.get(y).getPrice());
                System.out.println(webAutomations.get(y).getCourseTitle());
            }

    }

}
