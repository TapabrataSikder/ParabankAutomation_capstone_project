package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;

import java.util.HashMap;
import java.util.Map;

// TestListener observes the flow of the tests, logs to ExtentReports, and tells ScreenshotUtil to take ss
public class TestListener extends BaseTest implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static Map<String, ExtentTest> testMap = new HashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testMap.put(result.getMethod().getMethodName(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.PASS, "Test Passed Successfully");
    }

    @Override // annotation to override the default behaviour of onTestFailure defined by ITestListener
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        try {
            String screenshotPath = ScreenshotUtil.takeScreenshot(BaseTest.getDriver(), result.getName());
            System.out.println("Screenshot taken for failed test: " + result.getName());

            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}