The command 

- As the shown above we use RestAssured dependency and import package to use  it 

- As shown in  below we use this command to store the url link tou use it in the same method 

```
 RestAssured.baseURI= "https://rahulshettyacademy.com";

```

- We use **given**  to make all preparation ready before sent the request as iw we need to show logs or 
   - send something in header
  - or sent query-param  as which what param  we need to  send to make request pass   but not the end point which we need to talk
  -  and if and in header we send the type of request which we send 

```
 RestAssured.baseURI= "https://rahulshettyacademy.com";
        given().log().all().queryParam("Key","qacklick123").header("Content-Type","application/json")
                .body("code ").when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP")).extract().response().asString();
    }

```

- We use **when** to make the action as post("The end point which we need to use ") or get 

- We use **Then** to receive the outout of request and if we need to make assert as above 
 - the log() after then() it show the output   
 - and the header() after then() is to assert or inspect the header of response 
 -  we can use assertThat() to  tell the compiler  which coming after is asserted condition need to check on it as above 


### We use those 3 below methods to how extract response and how to use it

- We use  below to extract response as string and save it in variable 
```
.extract().response().asString()
 ```

- The method take string then convert it as json 
 ```
JsonPath js = new JsonPath(response);

```

- If you need to access field inside child which inside parent you access it by .

```
js.get("parent.child.place_id")
```


## Mock response 

- we can use to mock response to make it return specific response  dummy to test your work 
-  by make function to store the hard code response then pass it as json  till the real is coming
-  As shown below in mockComplesJS test as we define it 

```
JsonPath js  = new JsonPath(payLoadFirstSection.MockCoursePrice());

```
- we use this method to get size and number of courses it only valid using for array 
-  we getInt to get number 
```
 js.getInt("courses.size()");

```

- To get element form array of json we use command as below  it will enter the course search for the first course 
   - Then it get the title  use getString / get only the either each  of two make the output shown  

```
String title = js.getString("courses[0].title");

```

- If i need to print each course title and pice  git size of array and 
  - make for loop to go inside each course and use it to get the title and price of it 
  -  print the title by make i change as shown we make i dynamic 

```
 for( int i =0 ; i<js.getInt("courses.size()");i++)
        {
            System.out.println( js.getString("courses["+i+"].title"));
            System.out.println( js.getString("courses["+i+"].price"));
        }

```#   R e s t A s s u r e d F r a m e W o r k L E a r n i n g  
 