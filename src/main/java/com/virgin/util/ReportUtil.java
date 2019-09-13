package com.virgin.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportUtil {
	private ExtentHtmlReporter reporter;
	private ExtentReports extent;

	public ExtentHtmlReporter getReport() {
		reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
		return reporter;
	}

	public ExtentReports getExtent() {
		ExtentReports extent = new ExtentReports();
		return extent;
	}

	public ExtentTest createScenario(ExtentReports extents, String useCaseName) {
		ExtentTest logger = extents.createTest(useCaseName);
		return logger;
	}

	public void exit() {
		extent.flush();
	}

}
