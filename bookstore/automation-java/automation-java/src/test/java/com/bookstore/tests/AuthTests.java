package com.bookstore.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.*;

import io.restassured.RestAssured;

public class AuthTests {
	
	@Test
	public void testUserLoginAndSignup() {
		RestAssured.baseURI= "http://localhost:8000";
		
		String randomUsername =  "apitestuser_" + System.currentTimeMillis();
		
		JSONObject user = new JSONObject();
		user.put("username",randomUsername);
		user.put("password","apitestpass");
		
		//signup
		given().contentType("application/json").body(user.toString())
		.when().post("/signup").then().statusCode(200);

		
		//login
		given().contentType("application/json")
		.body(user.toString())
		.when().post("/login")
		.then().statusCode(200).body("access_token", notNullValue());
	}
	
}
