package com.virgin.page;

import org.openqa.selenium.By;

import com.virgin.enums.ShippingMethod;
import com.virgin.util.DriverUtil;

public class CheckOut_CartPage extends PageObject {

	ShippingMethod shippingMethod = ShippingMethod.FREE;
	private String invalidPromotionalCodeMessage = "The promo code you entered is invalid. Please try again.";

	// BY
	public By RDB_SHIPPING_METHOD = By.xpath("(//small[@class='control-indicator'])['"
					+ shippingMethod.getShippingMethod() + "']");

	public By INP_PROMOTIONAL_CODE = By.id("promo_Code");

	public By BTN_APPLY_PROMOTIONAL_CODE = By
			.xpath("//button[text()='apply code']");

	public By DIV_CODE_VALIDATION_MESSAGE = By
			.xpath("//div[@class='validation_message']");

	public By BTN_CONTINUE = By
			.xpath("//a[text()='next: billing and shipping']");

	/***********/

	public CheckOut_CartPage(DriverUtil driverUtil) {
		super(driverUtil);
	}

	public void selectShippingMethod(ShippingMethod shippingMethod) {		
		setRDB_SHIPPING_METHOD(shippingMethod);
		driverUtil.click(RDB_SHIPPING_METHOD);
	}

	public String applyPromotionalCode(String code) {
		driverUtil.sendText(INP_PROMOTIONAL_CODE, code);
		driverUtil.click(BTN_APPLY_PROMOTIONAL_CODE);
		return driverUtil.waitForElementVisibility(DIV_CODE_VALIDATION_MESSAGE)
				.getText();
	}

	public void clickContinue() {
		driverUtil.click(BTN_CONTINUE);
	}

	public String getInvalidPromotionalCodeMessage() {
		return this.invalidPromotionalCodeMessage;
	}

	public void setRDB_SHIPPING_METHOD(ShippingMethod shippingMethod) {
		RDB_SHIPPING_METHOD = By
				.xpath("(//small[@class='control-indicator'])['"
						+ shippingMethod.getShippingMethod() + "']");
	}
}
