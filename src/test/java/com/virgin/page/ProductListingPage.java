package com.virgin.page;

import org.openqa.selenium.By;

import com.virgin.util.DriverUtil;

public class ProductListingPage extends PageObject{			
	
	public By CHK_BRAND = By.xpath("//span[text()='Apple']");	
	public By BTN_SELECTDEVICE = By.xpath("(//a[text()='select this device'])[1]");	
		
	public ProductListingPage(DriverUtil driverUtil){
		super(driverUtil);
	}
	
	public void filterByBrand(String brand){		
		setCHK_BRAND(brand);
		driverUtil.click(CHK_BRAND);
	}
	
	public void selectProduct(String productIndex){
		setBTN_SELECTDEVICE(productIndex);
		driverUtil.click(BTN_SELECTDEVICE);		
	}

	public void setCHK_BRAND(String brand) {
		this.CHK_BRAND = By.xpath("//span[text()='"+brand+"']");
	}	
	
	public void setBTN_SELECTDEVICE(String productIndex) {				
		BTN_SELECTDEVICE = By.xpath("(//a[text()='select this device'])["+productIndex.substring(0, productIndex.indexOf("."))+"]");
	}
}
