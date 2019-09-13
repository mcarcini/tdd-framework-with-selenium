package com.virgin.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.virgin.interfaces.TestConfiguration;
import com.virgin.util.ConfigDriver;
import com.virgin.util.Const;
import com.virgin.util.DriverUtil;
import com.virgin.util.ReportUtil;

public abstract class AbstractTest implements TestConfiguration{

	protected String URL;
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	protected DriverUtil driverUtil;

	// Report
	protected ReportUtil report;
	protected ExtentHtmlReporter reporter;
	protected ExtentReports extent;
	protected ExtentTest logger;

	public AbstractTest() {
	}

	@BeforeTest
	public void beforeTest() {		
		driver = ConfigDriver.instantiateBrowser();
		driverWait = ConfigDriver.getWebDriverWait();
		driverUtil = new DriverUtil(driver, driverWait);
		initReport();
		beforeTestExtraSetup();
	}

	public void beforeTestExtraSetup() {
	}

	@AfterTest
	public void afterTest() {		
		driver.quit();
		driverWait = null;
		driverUtil = null;
		afterTestExtraSetup();
	}

	public void afterTestExtraSetup() {

	}
	

	public void initReport() {
		report = new ReportUtil();
		reporter = report.getReport();
		extent = report.getExtent();
		extent.attachReporter(reporter);		
	}
}
