package com.bookstore.tests;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class BaseTest {


	protected static String authHeader;	
	
	@BeforeClass
	public void baseSetUp() {
		RestAssured.baseURI = "http://localhost:8000";
		
		String username = "user" + System.currentTimeMillis() + "@test.com";
		String password = "pass123";

		JSONObject creds = new JSONObject();
		creds.put("email", username);
		creds.put("password", password);
		
		//sign up
		given().contentType("application/json")
		.body(creds.toString())
		.when().post("/signup")
		.then().statusCode(200);
		
		//log in 
		Response response = given().contentType("application/json")
		.body(creds.toString())
		.when().post("/login")
		.then().statusCode(200)
		.extract().response();
		
		authHeader = "Bearer " + response.jsonPath().getString("access_token");
		
	}
}
