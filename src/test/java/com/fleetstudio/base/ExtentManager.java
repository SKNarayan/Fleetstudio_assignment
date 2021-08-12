package com.fleetstudio.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager{

    private static ExtentReports extentReports;
    private static ExtentHtmlReporter htmlReporter;

    public static ExtentReports createInstanse(){

        String fileName = getReportName();
        String reportDirectory = System.getProperty("user.dir") + "/reports/";
        new File(reportDirectory).mkdirs();
        String path = reportDirectory + fileName;

        htmlReporter = new ExtentHtmlReporter(path);

        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Fleetstudio_Assignment_Automation_Report");
        htmlReporter.config().setReportName("Fleetstudio_Assignment_Automation_Test_Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Organization", "Fleetstudio");
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Environment", "SIT");
        extentReports.setSystemInfo("TestEngineerName", "Shashi Narayan");
        extentReports.attachReporter(htmlReporter);

        return extentReports;


    }


    public static String getReportName(){

        Date date = new Date();
        String fileName = "AutomationReport_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";

        return fileName;
    }

}
