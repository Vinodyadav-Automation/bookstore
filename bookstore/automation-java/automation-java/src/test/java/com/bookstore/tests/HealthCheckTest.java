package com.bookstore.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

public class HealthCheckTest extends BaseTest{

	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Health Check")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void testHealthEndpoint() {
		RestAssured.given()
		.when().get("/health")
		.then()
		.statusCode(200);
		
	}
	
}
