package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;



public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extentReport = ExtentReporterNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		 test = extentReport.createTest(result.getMethod().getMethodName());
	  }
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		  }
	
	@Override
	  public void onFinish(ITestContext context) {
		extentReport.flush();
		}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		//get driver information from result
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		  }

}
