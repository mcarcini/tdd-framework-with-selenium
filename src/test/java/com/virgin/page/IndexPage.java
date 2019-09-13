package com.virgin.page;

import org.openqa.selenium.By;

import com.virgin.util.DriverUtil;

public class IndexPage extends PageObject{
			
	public By LINK_SHOP = By.xpath("//a[contains(text(),'Shop')]");	
	
	public IndexPage(DriverUtil driverUtil){
		super(driverUtil);
	}

	public void clickMenu(By by){
		driverUtil.click(by);
	}
}
