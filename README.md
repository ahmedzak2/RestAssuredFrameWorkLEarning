 # RestAssured Framework Learning

- As the shown above we use RestAssured dependency and import package to use it
- As shown in below we use this command to store the url link to use it in the same method
  ```
  RestAssured.baseURI= "https://rahulshettyacademy.com";
  ```

- We use **given** to make all preparation ready before sending the request as if we need to show logs or
  - send something in header
  - or send query-param as which what param we need to send to make request pass but not the endpoint which we need to talk
  - and if and in header we send the type of request which we send
  ```
  RestAssured.baseURI= "https://rahulshettyacademy.com";
  given().log().all().queryParam("Key","qacklick123").header("Content-Type","application/json")
          .body("code ").when().post("maps/api/place/add/json")
          .then().log().all().assertThat().statusCode(200)
          .body("scope",equalTo("APP")).extract().response().asString();
  ```

- We use **when** to make the action as post("The endpoint which we need to use") or get
- We use **Then** to receive the output of request and if we need to make assert as above
  - the log() after then() it show the output
  - and the header() after then() is to assert or inspect the header of response
  - we can use assertThat() to tell the compiler which coming after is asserted condition need to check on it as above

### We use those 3 below methods to how extract response and how to use it
- We use below to extract response as string and save it in variable
  ```
  .extract().response().asString()
  ```
- The method take string then convert it as json
  ```
  JsonPath js = new JsonPath(response);
  ```

- If you need to access field inside child which inside parent you access it by.
  ```
  js.get("parent.child.place_id")
  ```

## Mock response
- we can use to mock response to make it return specific response dummy to test your work
- by make function to store the hard code response then pass it as json till the real is coming
- As shown below in mockComplesJS test as we define it
  ```
  JsonPath js = new JsonPath(payLoadFirstSection.MockCoursePrice());
  ```
- we use this method to get size and number of courses it only valid using for array
- we getInt to get number
  ```
  js.getInt("courses.size()");
  ```

- To get element from array of json we use command as below it will enter the course search for the first course
  - Then it get the title use getString / get only the either each of two make the output shown
  ```
  String title = js.getString("courses[0].title");
  ```

- If i need to print each course title and price git size of array and
  - make for loop to go inside each course and use it to get the title and price of it
  - print the title by make i change as shown we make i dynamic
  ```
  java
  for( int i =0; i<js.getInt("courses.size()"); i++) {
      System.out.println(js.getString("courses["+i+"].title"));
      System.out.println(js.getString("courses["+i+"].price"));
  }
  ```

- When you need to pass different combinations  when you run you test you use data provider 
   - then add functions which you define your data as shown

```
@DataProvider(name = "BookData")
    public Object[][] getData(){

        // array of the two elements which we need to send
       return new Object[][]{{"",""},{"",""},{"",""}};
    }

```
- then to use it use as shown 
  - As you see we add data provider to you test and then in function make it receive the data 
  - It runs the test as many times you make your array of data 

```

   @Test(dataProvider ="BookData")
    public void addBokk(String isbn, String asile ){
}
```

- If I have json in file to read form it we use
   -  and it converts all contents of file to byte 
   - Then you need to convert it to string  as shown

```
.body(new String(Files.readAllBytes(Paths.get("path"))))

```

## Integrate with Jira 

- First create there many to make authentication with api as in link  
  - https://developer.atlassian.com/cloud/jira/platform/rest/v2/intro/#authentication
- Will take about the Basic auth for rest api  as shown 
  - https://developer.atlassian.com/cloud/jira/platform/basic-auth-for-rest-apis/
- Follow the steps in supply basic auth headers

- When you send the token of login send it on format BASE64
  - To make sure you encrypted so  it not hack As shown 
 ```
Windows 7 and later, using Microsoft Powershell:

Copy
$Text = ‘user@example.com:api_token_string’
$Bytes = [System.Text.Encoding]::UTF8.GetBytes($Text)
$EncodedText = [Convert]::ToBase64String($Bytes)
$EncodedText

```
- I go to jira link to know how to create issue 
```
https://developer.atlassian.com/server/jira/platform/jira-rest-api-example-create-issue-7897248/

```
 ### Create bug 

```
String authHeader = "Basic " + "your_encoded_string_here"; 

        String response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"The login page is not opening - error 404\",\n" +
                        "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
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
                .statusCode(200)
                .extract().response().asString();


```
- We use the api token after endecoded it to use it to make login as way above 
 - Then send the key of project   it's unique value for each project and you can find it in project  setting 
 -  Then send the bug summary and description of bug  and how to use it 

### To Add screenshot to jira 
- Use MUltiPart to add screenshot 

```
 given()
                .header("X-Atlassian-Token", "no-check")
                .header("Authorization", authHeader).log().all()
                .multiPart("file",new File("D:\\TESTING\\AUtomations\\RestAssured\\src\\test\\resources\\ScreenshotA.png")).post("rest/api/2/issue/"+ ID+"/attachments").then().log().all().assertThat().statusCode(200);


```

### OAuth
- it make server which when you log in give you access to use it with all end point you need to use 
- Instead send you email and password every time 
- It depends on what type of access granted as show below 
   -  client credential   ( client ID - client screct )
   -  password credential ( user name -password)
  
- and you must determine what type of access granted as shown below 
-  You send your access as form params 

```
String response=  given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

            .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

            .formParams("grant_type", "client_credentials").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();


```

### POJO

- we separate the json file as in classes as shown in pojo folder 
 - and in  caode use the main calss or parent class of pojo  use it to convert the response for this class as shown in below 
```
GetCourse getCourse =given().queryParam("access_token",accessToken).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")

                .as(GetCourse.class);
    System.out.println(getCourse.getCourses().getWebAutomation().get(0).getCourseTitle());
        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());
```
- The methods are access the parent class then access the child class then access the variable inside it to get the value 
-  This to take response of json then convert it to values inside variable inside class this is called Deserialization

### Serialization
- convert Java class to json file to send it 


### request & response  Spec Builder  
- To make variable which has the build of project 
- we create it externally and added all information we need  we will put in it all  information which common between test cases   as  shown below 
```
  RequestSpecification requestSpecBuilder=   new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
              .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
RequestSpecification req =  given().spec(requestSpecBuilder).log().all().body(place);
```
- This is make the request take all common information and save it in variable of type  RequestSpecification to use it more than on time 
-  and you can make another request  which have the body  attached to it 
- If we talk about response  it will follow same as behaviour of request 
```
ResponseSpecification responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        
```

## how TO create list of array of items 

- when you have as example more than one order and each order has its details and  
- Yon need to create all orders So you make class for each order as in Order details and add its all information 
- Then make list of orders and pass all order detail
```
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

```

### Note important 
- you can use  .rel axedHTTPSValidation() after the request given()  to bypass the ccl certificates in case of proxy or other things  
```
RequestSpecification requestSpecificationDelete= given().relaxedHTTPSValidation().spec(createAndDeleteOrderReq).log().all().pathParam("productId",productId);


```


### Save logs in file 

```
public RequestSpecification requestSpecification () throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream("Logging.txt"));
        return new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .build();

    }
```
- As you see we add addFilter(RequestLoggingFilter.logRequestTo(printStream) 
- method in RestAssured is used to log the details of HTTP requests to a specified output stream, 
   - such as System.out or another custom PrintStream.
   - RequestLoggingFilter: This is a filter provided by RestAssured that intercepts and logs the HTTP request.
   - logRequestTo(PrintStream): This method of RequestLoggingFilter configures the filter to log the details of the request to the provided PrintStream object.
