package files;

import io.restassured.path.json.JsonPath;

public class MockComplexJS {
    public static void main(String []args){
        //to get the mock response
        JsonPath js=new JsonPath(payLoadFirstSection.MockCoursePrice());
      int count =  js.getInt("courses.size()");
        System.out.println(count);
        int amount =js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);
        String title = js.getString("courses[0].title");
        System.out.println(title);
        for( int i =0 ; i<count;i++)
        {
            System.out.println( js.getString("courses["+i+"].title"));
            System.out.println( js.getString("courses["+i+"].price"));
        }

    }
}
