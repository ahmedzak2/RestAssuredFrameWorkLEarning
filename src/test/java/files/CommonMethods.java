package files;

import io.restassured.path.json.JsonPath;

public class CommonMethods {

    public static JsonPath  rawToJson( String response){
        JsonPath js1=new JsonPath(response); //for parsing Json
        return js1;
    }
}
