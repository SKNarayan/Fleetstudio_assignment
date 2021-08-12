package com.fleetstudio.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class TestListeners implements ITestListener {

    private static ExtentReports extentReports = ExtentManager.createInstanse();

    //ThreadLocal is use for making this class thread safe, helpful for parallel testing
    private static ThreadLocal<ExtentTest> extentText = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result){
        ExtentTest test = extentReports.createTest(result.getTestClass().getName()
                + " :: " + result.getMethod().getMethodName());

        //setting this above test into the threadLocal to make thread safe
        extentText.set(test);

    }

    public void onTestSuccess(ITestResult result){
        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

        //using get() method because we are accessing through threadLocal
        extentText.get().log(Status.PASS, markup);
    }

    public void onTestFailure(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());

        extentText.get().fail("<details><summary><b><font color=red>" +
                "Exception Occured, Click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>" + "</details> \n"));

        WebDriver driver = ((SetUp)result.getInstance()).driver;
        String path = takeScreenshot(driver, result.getMethod().getMethodName());
        try{
            extentText.get().fail("<b><font color=red>" + "Failure Screenshot" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }catch (IOException e){
            extentText.get().fail("Test Failed, cannot attach screenshot");

        }

        String logText = "<b>Test Method " + methodName + " Failed</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentText.get().log(Status.FAIL, markup);


    }

    public void onTestSkipped(ITestResult result){
        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);

        extentText.get().log(Status.SKIP, markup);
    }

    public void onStart(ITestContext context){

    }

    public void onFinish(ITestContext context){
        if(extentReports != null){
            extentReports.flush();
        }
    }




    public String takeScreenshot(WebDriver driver, String methodName){
        String fileName = getScreenshotName(methodName);
        String directory = System.getProperty("user.dir") + "/screenshots/";
        new File(directory).mkdirs();

        String path = directory + fileName;

        try{
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));

            System.out.println("*******************************************");
            System.out.println("Screenshot Location: " + path);
            System.out.println("*******************************************");

        }catch (Exception e){
            e.printStackTrace();
        }

        return path;
    }

    public static String getScreenshotName(String methodName){
        Date date = new Date();
        String fileName = methodName + "_" + date.toString().replace(":", "_")
                .replace(" ", "_") + ".png";

        return fileName;
    }













}
