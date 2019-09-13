package com.virgin.page;

import org.openqa.selenium.By;

import com.virgin.util.DriverUtil;

public class ProductDetailPage extends PageObject{
		
	public By H2_PRODUCT_NAME = By.xpath("//h2[@class='phone-name']");
	public By BTN_ADD_TO_CART = By.xpath("//button[text()='add to cart']");
	
	public ProductDetailPage(DriverUtil driverUtil){
		super(driverUtil);
	}
	
	public String getProductName(){
		return driverUtil.getText(H2_PRODUCT_NAME);
	}
	
	public void addProductToCart(){
		driverUtil.click(BTN_ADD_TO_CART);
	}

}
