package org.example.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    // Thread-safe test object
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getMethod().getMethodName());

        // Store this test in the thread-local map
        extentTest.set(test); //Assign a unique thread ID making it safe during parallel runs
        //with the thread id and the test it will create a map
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Test is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable()); //Error validation reaches here, it will first see the thread ID which is matching the test and hence it will get the correct test using this
        //test.fail(iTestResult.getThrowable());
        test.log(Status.FAIL, "Test is Failed.");

        //take screenshot and attach to Report
        try {
            // get driver from test class (reflection)
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
            //getTestClass - org.example.Tests.SubmitOrderTest
            //getRealClass - go to the real class which is using the field driver
            String screenshotPath = getScrenshot(iTestResult.getMethod().getMethodName(), driver);

            //ThreadLocal automatically returns the correct ExtentTest instance for that thread.
            extentTest.get().addScreenCaptureFromPath(screenshotPath, iTestResult.getMethod().getMethodName()); //there the extentTest will verify the thread id with the test running and hence attach the screenshot to the correct test case
            // test.addScreenCaptureFromPath(screenshotPath, iTestResult.getMethod().getMethodName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
