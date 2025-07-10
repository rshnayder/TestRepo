package com.example.api.tests;

import com.example.assertions.ApiAssertions;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected ApiAssertions assertions;

    @BeforeClass
    protected void initialize() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeMethod
    protected void prepareAssertions() {
        this.assertions = new ApiAssertions();
    }

    @AfterClass
    protected void afterAll() {
       RestAssured.reset();
    }
}
