package org.example.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+ "/reports/index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path); //this accepts a path where your report should be stored
        reporter.config().setReportName("Web Automation Results"); //set the name of the extent report which is displayed at the top
        reporter.config().setDocumentTitle("Test Results"); //Set the title of the report html page

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("tester", "Nancy Verma");

        return extent;
    }
}
