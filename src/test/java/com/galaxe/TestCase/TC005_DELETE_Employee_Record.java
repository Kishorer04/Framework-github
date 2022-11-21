package com.galaxe.TestCase;

import org.testng.Assert;
import org.testng.annotations.*;


import com.galaxe.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DELETE_Employee_Record extends TestBase {
  
	@BeforeClass
    public void deleteEmployee() throws InterruptedException {
		logger.info("****Started TC005****");
		RestAssured.baseURI="https://reqres.in/api/users";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		
	    //Capture id
		String empID=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/"+empID); //Pass ID to delete record
		
		Thread.sleep(3000);
	}
	
	
//	@Test
//	void checkResponseBody() throws InterruptedException {
//		logger.info("***Checking Response Body***");
//		String responseBody = response.getBody().asString();
//		logger.info("Response Body--->" + responseBody);
//		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
//		Thread.sleep(3000);
//	}

	@Test
	void checkStatusCode() throws InterruptedException {
		logger.info("***Checking Status code***");
		int statusCode = response.getStatusCode();
		logger.info("Status code is--->" + statusCode);
		Assert.assertEquals(statusCode, 204);
		Thread.sleep(3000);
	}
	
	@Test

	void checkStatusLine() throws InterruptedException {
		logger.info("***Checking Status Line***");
		String statusLine = response.getStatusLine();
		logger.info("Status line is--->" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
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
		logger.info("***DELETE Request Completed***");
	}

  
}
