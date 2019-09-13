package com.virgin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.virgin.util.DriverUtil;

public class CheckOut_PaymentPage extends PageObject {

	// CART INFORMATION
	public By DIV_PRODUCT_INFO = By.xpath("//div[@class='inner-flex']");
	public By SPAN_PRODUCT_PRICE = By
			.xpath("((//*[contains(@class, 'inner-flex')])[1])/span[last()]");
	public By SPAN_SHIPPING_COST = By
			.xpath("//*[contains(@class, 'inner-section inner-flex small')]//span");
	public By SPAN_TOTAL_PRICE = By
			.xpath("(//div[contains(@class, 'before-due')]//h4)");

	// PAYMENT INFORMATION
	public By INP_CARD_NUMBER = By.id("ecc-card-number");
	public By DLL_EXPIRATION_MONTH = By.name("expirationMonth");
	public By DLL_EXPIRATION_YEAR = By.name("expirationYear");
	public By INP_CVV = By.name("cvv");
	public By INP_CARD_HOLDER_NAME = By.name("cardholder-name");

	// ADDRESS INFORMATION
	public By INP_FIRST_NAME = By.name("fname");
	public By INP_LAST_NAME = By.name("lName");
	public By INP_ADDRESS = By.name("address");
	public By INP_CITY = By.name("City");
	public By DLL_STATE = By.name("state");
	public By INP_ZIP_CODE = By.name("zipCode");
	public By INP_EMAIL_ADDRESS = By.name("emailAddress");
	public By INP_CONFIRM_EMAIL = By.name("confirmEmail");
	
	public By CHK_TERMS = By.name("serviceTerms");
	public By BTN_REVIEW_ORDER= By.xpath("//button[contains(text(),'review order')]");

	public CheckOut_PaymentPage(DriverUtil driverUtil) {
		super(driverUtil);
	}

	public String getProductName() {
		String productInfo = driverUtil.getText(DIV_PRODUCT_INFO);
		if(productInfo.indexOf(",") == -1){
			if(productInfo.indexOf("$") > 0){
				return productInfo.substring(0, productInfo.indexOf("$")).trim();	
			}			
			return productInfo.trim();
		}else{
			return productInfo.substring(0, productInfo.indexOf(",")).trim();	
		}		
	}

	public Double getProductPrice() {
		String precioProductoStr = driverUtil.getText(SPAN_PRODUCT_PRICE)
				.substring(1);
		return Double.parseDouble(precioProductoStr);
	}

	public Double getShippingCost() {
		String precioEnvioStr = driverUtil.getText(SPAN_SHIPPING_COST)
				.substring(1);
		return Double.parseDouble(precioEnvioStr);
	}

	public Double getTotalPrice() {
		String precioTotal = driverUtil.getText(SPAN_TOTAL_PRICE).substring(1);
		return Double.parseDouble(precioTotal);
	}

	public void fillCreditCardInformationForm(String cardNumber,
			String expirationMonth, String expirationYear, String cvv,
			String cardHolderName) {
		driverUtil.sendText(INP_CARD_NUMBER, cardNumber);
		driverUtil
				.selectDLLByVisibleText(DLL_EXPIRATION_MONTH, expirationMonth);
		driverUtil.selectDLLByVisibleText(DLL_EXPIRATION_YEAR, expirationYear);
		driverUtil.sendText(INP_CVV, cvv);
		driverUtil.sendText(INP_CARD_HOLDER_NAME, cardHolderName);
	}

	public void fillAddressInformation(String firstName, String lastName,
			String address, String city, String state, String zipCode,
			String email, String emailConfirmation) {
		driverUtil.sendText(INP_FIRST_NAME, firstName);
		driverUtil.sendText(INP_LAST_NAME, lastName);
		driverUtil.sendText(INP_ADDRESS, address);
		driverUtil.sendText(INP_CITY, city);
		driverUtil.selectDLLByVisibleText(DLL_STATE, state);
		driverUtil.sendText(INP_ZIP_CODE, zipCode);
		driverUtil.sendText(INP_EMAIL_ADDRESS, email);
		driverUtil.sendText(INP_CONFIRM_EMAIL, emailConfirmation);
	}
	
	public void acceptTerms(){
		driverUtil.jsClick(CHK_TERMS);
	}
	
	public void clickReviewOrder(){
		driverUtil.click(BTN_REVIEW_ORDER);
	}

}
