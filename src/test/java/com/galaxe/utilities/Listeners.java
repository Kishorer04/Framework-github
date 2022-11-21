package com.galaxe.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners implements ITestListener{
	
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	
	public void configureReport() {
		htmlReporter = new ExtentSparkReporter("ExtentReportKishore.html");
		reports= new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		
		//add system information/environment info to reports
		reports.setSystemInfo("OS", "Windows 11");
		reports.setSystemInfo("Browser", "Chrome");
		reports.setSystemInfo("User", "Kishore");
		
		
		//configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("API Framework Test");
		htmlReporter.config().setReportName("API Test");
		htmlReporter.config().setTheme(Theme.DARK);
	}  
	
	public void onStart(ITestContext Resullt) {
		configureReport();
		System.out.println("On Start method invoked");
	}
	
	
	public void onFinish(ITestContext Result) {
		System.out.println("On finished method invoked");
		reports.flush();
	}
	
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of test method succeeded "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.PASS,MarkupHelper.createLabel("Name of succeeded test case is: "+ Result.getName(),ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult Result) {
		System.out.println("Failure method invoked");
	    test=reports.createTest(Result.getName()); 
	    test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed testcase is:  "+Result.getName(),ExtentColor.RED));
	}
	
	
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of test method skipped: "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.SKIP,MarkupHelper.createLabel("Name of skipped test case is: "+Result.getName(),ExtentColor.ORANGE));
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		
	}
}


















//package com.galaxe.utilities;

//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestListener;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.TestListener;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;

//
//public class Listeners implements ITestListener{
//  
	
	
	
//	public ExtentHtmlReporter htmlReporter;
//	public ExtentReports extent;
//	public static ExtentTest test;
//	
//	
//	public void onTestStart(ITestResult result) {
//		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myExtentReport.html");//specify location for report
//		htmlReporter.config().setDocumentTitle("Automation Report");
//		htmlReporter.config().setReportName("Rest API testing");
//		htmlReporter.config().setTimeStampFormat("dd:mm:yyyy");
//		htmlReporter.config().setTheme(Theme.DARK);
//		
//		
//		extent=new ExtentReports();
//		extent.attachReporter(htmlReporter);
//		extent.setSystemInfo("Host name", "localhost");
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("user", "Kishore");
//		
//}
//	
////	public void onTestStart(ITestResult result) {
////		test=extent.createTest(result.getName());
////	}
//	
//	
//	public void onTestSuccess(ITestResult result) {
//		if(result.getStatus()==ITestResult.SUCCESS)
//		test.log(Status.PASS,"TEST CASE PASSED IS"+result.getName());
//		//test=extent.createTest(result.getName()); //create new entry in the report
//	}
//	
//	
//	public void onTestFailure(ITestResult result) {
//		//test=extent.createTest(result.getName());//create new entry in the report
//		if(result.getStatus()==ITestResult.FAILURE) {
//		test.log(Status.FAIL,MarkupHelper.createLabel(result.getName(), null)); //to add name in extent report
//		test.log(Status.FAIL,"TEST CASE FAILED IS"+result.getThrowable());//to add error/exception in extent report
//	}
//	}
//	
//	
//	public void onTestSkipped(ITestResult result) {
//		//test=extent.createTest(result.getName());//create new entry in the report
//		if(result.getStatus()==ITestResult.SKIP)
//		test.log(Status.SKIP,"test case skipped is"+result.getName());
//	}
//	
//	
//	public void teardown(ITestResult result) {
//		extent.flush();
//	}
	
//}
