package example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.EcommerceAPI.Login;
import pojo.EcommerceAPI.LoginResponse;
import pojo.EcommerceAPI.Order;
import pojo.EcommerceAPI.OrderDetail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceAPITest {
    RequestSpecification requestSpecificationUrl=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
    Login login =new Login();
    @Test
    public void testEcommerce(){
        login.setUserEmail("rahulshetty@gmail.com");
        login.setUserPassword("Iamking@000");

RequestSpecification requestSpecificationBody =given().spec(requestSpecificationUrl).body(login);
LoginResponse responseLoginBody =requestSpecificationBody.when().log().all().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
        System.out.println(responseLoginBody.getToken());
    String token = responseLoginBody.getToken();
        String userId =responseLoginBody.getUserId();

    RequestSpecification requestSpecificationUrlAddProduct=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();
RequestSpecification requestSpecificationAddProduct= given().log().all().spec(requestSpecificationUrlAddProduct).param("productName","laptob")
        .param("productAddedBy", userId).param("productCategory", "fashion")
        .param("productSubCategory", "shirts").param("productPrice", "11500")
        .param("productDescription", "Lenova").param("productFor", "men")
        .multiPart("productImage",new File("C:/Users/20112/OneDrive/Pictures/Screenshots/ScreenshotA.png"));

String responseSpecificationAddProduct = requestSpecificationAddProduct.when().log().all().post("/api/ecom/product/add-product").then().log().all()
        .extract().response().asString();

    JsonPath jsonPath = new JsonPath(responseSpecificationAddProduct);
   String productId= jsonPath.getString("productId");

   RequestSpecification createAndDeleteOrderReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).setContentType(ContentType.JSON).build();
        OrderDetail orderDetail =new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderId(productId);
        OrderDetail orderDetail2 =new OrderDetail();
        orderDetail2.setCountry("Egypt");
        orderDetail2.setProductOrderId(productId);
        List<OrderDetail> orderDetailList =new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);
        Order order =new Order();
        order.setOrders(orderDetailList);

     RequestSpecification createOrderReqBody = given().spec(createAndDeleteOrderReq).body(order);
 String responseOrder =    createOrderReqBody.when().log().all().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseOrder);
        JsonPath jsonPathOrder = new JsonPath(responseOrder);
RequestSpecification requestSpecificationDelete= given().relaxedHTTPSValidation().spec(createAndDeleteOrderReq).log().all().pathParam("productId",productId);
String responseDeete=requestSpecificationDelete.when().log().all().delete("/api/ecom/product/delete-product/{productId}").then().statusCode(200).extract().response().asString();
        JsonPath jsonPathDeleteOrder = new JsonPath(responseOrder);
        System.out.println("Message are "+jsonPathDeleteOrder.getString("message"));
    }
}
