package files;

public class PayLoadFirstSection {

    public static String addPlace(){

         String object=  "{\r\n" +
                 "  \"location\": {\r\n" +
                 "    \"lat\": -38.383494,\r\n" +
                 "    \"lng\": 33.427362\r\n" +
                 "  },\r\n" +
                 "  \"accuracy\": 50,\r\n" +
                 "  \"name\": \"Rahul Shetty Academy\",\r\n" +
                 "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
                 "  \"address\": \"29, side layout, cohen 09\",\r\n" +
                 "  \"types\": [\r\n" +
                 "    \"shoe park\",\r\n" +
                 "    \"shop\"\r\n" +
                 "  ],\r\n" +
                 "  \"website\": \"http://rahulshettyacademy.com\",\r\n" +
                 "  \"language\": \"French-IN\"\r\n" +
                 "}\r\n" +
                 "";
    return object;
    }

    public static String MockCoursePrice(){
         return "{\r\n" +
                "  \"dashboard\": {\r\n" +
                "    \"purchaseAmount\": 1163,\r\n" +
                "    \"website\": \"rahulshettyacademy.com\"\r\n" +
                "  },\r\n" +
                "  \"courses\": [\r\n" +
                "    {\r\n" +
                "      \"title\": \"Selenium Python\",\r\n" +
                "      \"price\": 50,\r\n" +
                "      \"copies\": 6\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"Cypress\",\r\n" +
                "      \"price\": 40,\r\n" +
                "      \"copies\": 4\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"RPA\",\r\n" +
                "      \"price\": 45,\r\n" +
                "      \"copies\": 10\r\n" +
                "    },\r\n" +
                "     {\r\n" +
                "      \"title\": \"Appium\",\r\n" +
                "      \"price\": 36,\r\n" +
                "      \"copies\": 7\r\n" +
                "    }\r\n" +
                "    \r\n" +
                "    \r\n" +
                "    \r\n" +
                "  ]\r\n" +
                "}\r\n" +
                "";



    }

    public static String AddBook(String isbn,String asile)
    {
        String s = "{\r\n" +"r\n" +
                "\"name\": \"way to survive\",\r\n" +
                "\"isbn\": \""+isbn+"\",\r\n" +
                "\"aisle\": \""+asile+"\",\r\n" +
                "\"author\": \"ahmed ziko\",\r\n" +
                "  }\r\n" + "\n"+"";
return s;
    }

}
