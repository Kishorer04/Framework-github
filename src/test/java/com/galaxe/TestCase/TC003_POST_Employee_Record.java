package com.galaxe.TestCase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.galaxe.base.TestBase;
import com.galaxe.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_POST_Employee_Record extends TestBase {

	String empName = RestUtils.empName();
	//String empSalary = RestUtils.empSal();
	//String empAge = RestUtils.empAge();

	@SuppressWarnings("unchecked")

	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("****Started TC003****");
		RestAssured.baseURI = "https://reqres.in/api/users";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value
		// pairs using the put method

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("job","teacher" );
		//requestParams.put("employee_age", empAge);

		// Add a header stating the request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request

		httpRequest.body(requestParams.toJSONString());
		Thread.sleep(5000);
		response = httpRequest.request(Method.POST,"/create");
		
	}

	@Test
	void checkResponseBody() throws InterruptedException {
		logger.info("***Checking Response Body***");
		String responseBody = response.getBody().asString();
		logger.info("Response Body--->" + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains("teacher"), true);
		//Assert.assertEquals(responseBody.contains(empAge), true);
		Thread.sleep(3000);
	}

	@Test
	void checkStatusCode() throws InterruptedException {
		logger.info("***Checking Status code***");
		int statusCode = response.getStatusCode();
		logger.info("Status code is--->" + statusCode);
		Assert.assertEquals(statusCode, 201);
		Thread.sleep(3000);
	}

	@Test

	void checkStatusLine() throws InterruptedException {
		logger.info("***Checking Status Line***");
		String statusLine = response.getStatusLine();
		logger.info("Status line is--->" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		Thread.sleep(3000);
	}

//	@Test
//	void checkContentType() throws InterruptedException {
//		logger.info("***Checking Content type***");
//		String contentType = response.header("Content-Type");
//		logger.info("Content type is" + contentType);
//		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
//		Thread.sleep(3000);
//	}

	@Test
	void checkServerType() throws InterruptedException {
		logger.info("*****Checking Server Type*****");
		String serverType = response.header("Server");
		logger.info("Server type is--->" + serverType);
		Assert.assertEquals(serverType, "cloudflare");
		Thread.sleep(3000);

	}

	@AfterClass
	void finish() {
		logger.info("***POST Request Completed***");
	}
}
