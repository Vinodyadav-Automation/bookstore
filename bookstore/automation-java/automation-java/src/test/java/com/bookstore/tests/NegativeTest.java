package com.bookstore.tests;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class NegativeTest extends BaseTest{

	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Unauthorized access")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Should return 403 accessing without auth token")
	public void testCreateBookWithoutAuth() {
		JSONObject book = new JSONObject();
		book.put("name", "Advanced JAVA");
		book.put("author", "Vinod");
		book.put("book_summary", "JAVA for experts");
		book.put("published_year", "2019");
		
		given().contentType("application/json")
		.body(book.toString())
		.when()
		.post("/books/")
		.then()
		.statusCode(403);
	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Validation Error")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "should return 400 or 422 for missing required fields")
	public void testCreateBookInvalidPayload() {
		JSONObject book = new JSONObject();

		given().contentType("application/json")
		.header("Authorization", authHeader)
		.body(book.toString())
		.when()
		.post("/books/")
		.then()
		.statusCode(anyOf(is(422),is(400),is(500)));
	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Non-Existent Resource")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "should get 404 for a non existing book")
	public void testUpdateNotExisitentBook() {
		JSONObject updatedbook = new JSONObject();
		updatedbook.put("name", "JAVA AUtomation");
		updatedbook.put("author", "Vinod Kumar Yadav");
		updatedbook.put("book_summary", "Test Automation");
		updatedbook.put("published_year", "2020");
		
		given().header("Authorization", authHeader)
		.contentType("application/json").body(updatedbook.toString())
		.when().put("/books/999999")
		.then().statusCode(404);
		
	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("UnAuthorized Access")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "should return 403 while deleteing without token")
	public void testDeleteBookWithoutToken() {
		given()
		.when().delete("/books/1")
		.then().statusCode(403);
	}
}
