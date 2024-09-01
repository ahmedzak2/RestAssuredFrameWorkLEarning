package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class MockComplexJS {
    @Test
    public void Sumoftest(){

        //to get the mock response
        JsonPath js=new JsonPath(PayLoadFirstSection.MockCoursePrice());
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

        for( int i =0 ; i<count;i++)
        {
         String courseTitle= js.getString("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
               String copies= js.getString("courses["+i+"].copies");
                System.out.println("copy="+copies );
            }
            System.out.println( js.getString("courses["+i+"].price"));
        }
        System.out.println("amount "+amount);
    }
    @Test
    public void sumOfAll (){
        int sum =0;

        JsonPath js=new JsonPath(PayLoadFirstSection.MockCoursePrice());
        int count =  js.getInt("courses.size()");
        int amount =js.getInt("dashboard.purchaseAmount");
        for( int i =0 ; i<count;i++)
        {

            int coursePrice= js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int totalAmount = copies*coursePrice;


            sum=sum+totalAmount;

        }
        Assertion assert2= new Assertion();
        assert2.assertEquals(sum+1,amount,"there wrong in amount ");
        Assert.assertEquals(sum,amount,"there wrong in amount ");
        System.out.println("sum "+sum);

    }
    }


