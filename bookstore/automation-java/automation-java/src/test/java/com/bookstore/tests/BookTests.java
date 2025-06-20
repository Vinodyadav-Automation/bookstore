package com.bookstore.tests;

import static io.restassured.RestAssured.*;
import org.json.JSONObject;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookTests extends BaseTest{

//	private static String authHeader;
	private static int bookId;
	
	
//	@BeforeClass
//	public void setUp() {
//		RestAssured.baseURI = "http://localhost:8000";
//		
//		String username = "user" + System.currentTimeMillis() + "@test.com";
//		String password = "pass123";
//
//		JSONObject creds = new JSONObject();
//		creds.put("email", username);
//		creds.put("password", password);
//		
//		//sign up
//		given().contentType("application/json")
//		.body(creds.toString())
//		.when().post("/signup")
//		.then().statusCode(200);
//		
//		//log in 
//		Response response = given().contentType("application/json")
//		.body(creds.toString())
//		.when().post("/login")
//		.then().statusCode(200)
//		.extract().response();
//		
//		authHeader = "Bearer " + response.jsonPath().getString("access_token");
//		
//	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Create Book")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=1, description="Verify that a book can be created")
	public void testCreateBook() {
			
			
		//3. Create a book
		JSONObject book = new JSONObject();
		book.put("name", "API AUtomation");
		book.put("author", "Vinod Yadav");
		book.put("book_summary", "Test Automation for APIs");
		book.put("published_year", "2025");

		
		Response createRes = given().header("Authorization", authHeader)
				.contentType("application/json")
				.body(book.toString())
				.when().post("/books/")
				.then().statusCode(200)
				.extract().response();
		
		bookId = createRes.jsonPath().getInt("id");
		
		
	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Read Book")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2, dependsOnMethods = "testCreateBook", description = "Verify that a book can be read")
	public void testReadBook() {
		
		//4. Get book by ID
		given().header("Authorization", authHeader)
		.when().get("/books/"+ bookId)
		.then().statusCode(200)
		.body("name", equalTo("API AUtomation"));
		
		
	}
	
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Update Book")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3, dependsOnMethods = "testReadBook", description = "Verify that a book can be updated")
	public void testUpdateBook() {
		
		JSONObject updatedbook = new JSONObject();
		updatedbook.put("name", "JAVA AUtomation");
		updatedbook.put("author", "Vinod Kumar Yadav");
		updatedbook.put("book_summary", "Test Automation");
		updatedbook.put("published_year", "2020");
		
		given().header("Authorization", authHeader)
		.contentType("application/json").body(updatedbook.toString())
		.when().put("/books/"+ bookId)
		.then().statusCode(200)
		.body("name", equalTo("JAVA AUtomation"));
		
	}	
		
	@Epic("Bookstore API")
	@Feature("Book Management")
	@Story("Delete Book")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4, dependsOnMethods = "testUpdateBook", description = "Verify that a book can be deleted")
	public void testDeleteBook() {
		
		given().header("Authorization", authHeader)
		.when().delete("/books/"+bookId)
		.then().statusCode(200);
		
		//Confirm Deletion of the book
		given().header("Authorization", authHeader)
		.when().get("/books/"+bookId)
		.then().statusCode(404);
				
	}
}
