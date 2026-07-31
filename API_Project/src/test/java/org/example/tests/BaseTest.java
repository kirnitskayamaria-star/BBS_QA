package org.example.tests;

import io.restassured.RestAssured;
import org.example.utils.ApiConfig;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
    }
}
