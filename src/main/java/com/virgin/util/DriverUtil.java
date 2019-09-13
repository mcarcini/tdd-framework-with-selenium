package com.virgin.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtil {

	private WebDriver driver;
	private WebDriverWait driverWait;

	public DriverUtil(WebDriver driver) {
		this.driver = driver;
	}

	public DriverUtil(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;
	}

	public void switchToNewWindow() {
		String currentWindow = driver.getWindowHandle();

		for (String window : driver.getWindowHandles()) {
			if (currentWindow.equals(window))
				continue;
			driver.switchTo().window(window);
		}
	}

	public void closeNewWindow() {
		String currentWindow = driver.getWindowHandle();

		for (String window : driver.getWindowHandles()) {
			if (currentWindow.equals(window))
				continue;
			driver.switchTo().window(window);
			driver.close();
			driver.switchTo().window(currentWindow);
		}
	}

	public void jsClick(By by) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(by));
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public void sendText(By by, String texto) {
		driver.findElement(by).sendKeys(texto);
	}
	
	public void selectDLLByVisibleText(By by, String texto){
		Select select = new Select(driver.findElement(by));				
		select.selectByVisibleText(texto);
	}

	public WebElement waitForElementVisibility(By byLocator) {
		return driverWait.until(ExpectedConditions
				.visibilityOfElementLocated(byLocator));
	}

	public String getText(By by) {
		return driver.findElement(by).getText().trim();
	}	

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriverWait(WebDriverWait driverWait) {
		this.driverWait = driverWait;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	

}
