package com.example.assertions;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class Assertions {

 public static void assertStatusCode(Response response, int expectedStatusCode) {
         assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code");
     }

     public static void assertResponseBody(Response response, String expectedBody) {
         assertEquals(response.getBody().asString(), expectedBody, "Unexpected response body");
     }
}
