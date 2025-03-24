package com.Utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ActionDriver.Action;
import com.Base.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ListenerClass extends ExtentManager implements ITestListener {

	Action action= new Action();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			try {
		        // Log test pass details
		        test.log(Status.PASS,
		                MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));

		        // Capture and attach screenshot on success
		        String imgPath = Action.screenShot(BaseClass.getDriver(), result.getName());
		        test.pass("Screenshot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			test.log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			try {
				test.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				test.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				String imgPath = Action.screenShot(BaseClass.getDriver(), result.getName());
			
				test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
				
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.log(Status.WARNING, "Test failed but within success percentage: " + result.getName());
	}

	public void onStart(ITestContext context) {
		  System.out.println("Starting test suite: " + context.getName());

	}

	public void onFinish(ITestContext context) {
		 extent.flush();
	        System.out.println("Finished test suite: " + context.getName());
	}
}
