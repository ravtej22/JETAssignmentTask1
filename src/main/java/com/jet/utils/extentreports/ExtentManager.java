package com.jet.utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./Test-reports/Test-report.html");
        reporter.config().setReportName("Test Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Ravtej Singh");
        return extentReports;
    }
}
