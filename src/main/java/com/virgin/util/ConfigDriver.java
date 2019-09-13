package com.virgin.util;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.virgin.util.Const.Browser;

public class ConfigDriver {
	private static WebDriver driver;
	private static int IMPLICIT_TIMEOUT_SEC = Const.IMPLICIT_TIMEOUT_SEC;
	private static int EXPLICIT_TIMEOUT_SEC = Const.EXPLICIT_TIMEOUT_SEC;

	public static WebDriver instantiateBrowser(Browser browserType) {
		driver = getWebDriver(browserType);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_SEC, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver instantiateBrowser() {
		return instantiateBrowser(Const.Browser.CHROME);
	}

	private static WebDriver getWebDriver(Browser browserType) {
		switch (browserType) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		case IE:
			WebDriverManager.edgedriver().setup();
			return new InternetExplorerDriver();
		case CHROME:
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			
			return new ChromeDriver(chromeOptions);
		default:
			throw new RuntimeException("Navegador no soportado!!");
		}
	}
	
	public static WebDriverWait getWebDriverWait(int timeOut){
		return new WebDriverWait(driver, timeOut);		
	}
	
	public static WebDriverWait getWebDriverWait(){
		return new WebDriverWait(driver, EXPLICIT_TIMEOUT_SEC);		
	}
}
