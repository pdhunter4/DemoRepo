package testcases.ExtentReporting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.utils.utilities;

public class Listeners implements ITestListener{
	
	ExtentReports extentReports = utilities.generateReporter();
	ExtentTest test;

	/*
	 * As we are using thread safe concept so we Use ThreadLocal
	 * we declare it as static so no other class can access this object
	 */
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Start the 
		test = extentReports.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Success
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName()  + " is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Test Failed
		extentTest.get().fail(result.getThrowable());
		
		//Now add screenshot to the extent report for the failed testcase
		String testcaseName = result.getMethod().getMethodName();
		
		/*
		 *  So now we have to pass the driver of the testcase which is running in to error so there may be 
		 *  possibility that we can have 2 testcase failed at a time so the screenshot of that testcase should be taken 
		 *  by that driver only
		 */
		WebDriver driver = null;
		Object testObject = result.getInstance();	// get the instance of the testcase which is failing
		Class testClass = result.getTestClass().getRealClass();	//This is the class which contains the failed method
		try {
			// This will get the declared field in the class and as there could be multiple testcases
			// which could fail so we hyave to get the instance of the test method which is failing so that driver is captured
			driver = (WebDriver)testClass.getDeclaredField("driver").get(testObject);	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			extentTest.get().addScreenCaptureFromPath(utilities.getScreenshotPath(driver, testcaseName), testcaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReports.flush();
	}

}
