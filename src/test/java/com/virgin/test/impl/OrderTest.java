package com.virgin.test.impl;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.virgin.enums.ShippingMethod;
import com.virgin.interfaces.TestConfiguration;
import com.virgin.page.*;
import com.virgin.test.AbstractTest;
import com.virgin.util.ExcelUtil;
import com.virgin.util.ReportUtil;

public class OrderTest extends AbstractTest {

	// POM CLASSES
	IndexPage indexPage;
	ProductListingPage productListingPage;
	ProductDetailPage productDetailPage;
	CheckOut_CartPage cartPage;
	CheckOut_PaymentPage paymentPage;

	@Override
	public void beforeTestExtraSetup() {
		setURL("https://www.virginmobileusa.com");
		createScenarioReport();
		initPOMClasses();
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public void createScenarioReport() {
		logger = report.createScenario(extent, "PlaceOrderTest");
	}

	public void initPOMClasses() {
		indexPage = new IndexPage(driverUtil);
		productListingPage = new ProductListingPage(driverUtil);
		productDetailPage = new ProductDetailPage(driverUtil);
		cartPage = new CheckOut_CartPage(driverUtil);
		paymentPage = new CheckOut_PaymentPage(driverUtil);
	}

	@Override
	public void afterTestExtraSetup() {
		indexPage = null;
		productListingPage = null;
		productDetailPage = null;
		cartPage = null;
		paymentPage = null;
	}

	@DataProvider(name = "PlaceOrder")
	public static Object[][] placeOrderProvider() throws IOException {
		return ExcelUtil.readExcel("TestCases.xlsx", "PlaceOrderTest");
	}

	@Test(description = "Place Order", dataProvider = "PlaceOrder")
	public void placeOrderTest(String brand, String product)
			throws InterruptedException {

		try {
			driver.get(URL);

			// INDEX PAGE
			indexPage.clickMenu(indexPage.LINK_SHOP);

			// PRODUCT LISTING PAGE
			productListingPage.filterByBrand(brand);
			productListingPage.selectProduct(product);

			// PRODUCT DETAIL PAGE
			String nombreProductoEnCarrito = productDetailPage.getProductName();
			productDetailPage.addProductToCart();

			// CART PAGE
			cartPage.selectShippingMethod(ShippingMethod.NEXT_BUSINESS_DAY);
			String result = cartPage.applyPromotionalCode("codeX");

			/*** ASSERT ***/
			Assert.assertTrue(
					result.equals(cartPage.getInvalidPromotionalCodeMessage()),
					"El mensaje de codigo promocional incorrecto no coincide con el especificado");
			/*******************************************************************/
			cartPage.clickContinue();

			/*** ASSERT: validar nombres de productos ***/
			String nombreProductoEnPaginaPago = paymentPage.getProductName();

			Assert.assertTrue(
					nombreProductoEnCarrito.equals(nombreProductoEnPaginaPago),
					"No coincide el nombre de producto en pagina carrito y pagina pago");
			/*******************************************************************/

			/*** ASSERT: validar totales **/

			double precioProducto = paymentPage.getProductPrice();
			double precioEnvio = paymentPage.getShippingCost();
			double total = paymentPage.getTotalPrice();

			Assert.assertTrue((precioProducto + precioEnvio) == total,
					"La sumatoria de (precio producto + costo de envio) no coincide con el total");
			/*******************************************************************/

			// credit card information
			paymentPage.fillCreditCardInformationForm("4111111111111111",
					"January", "2023", "123", "Marco Antonio Carcini Mora");

			// AddressInformation
			paymentPage.fillAddressInformation("Marco Antonio", "Carcini Mora",
					"385 Walnut St", "Centreville", "AL", "35042",
					"marco.carcini@hotmail.com", "marco.carcini@hotmail.com");

			paymentPage.acceptTerms();
			paymentPage.clickReviewOrder();

			logger.log(Status.PASS, "Correcto");			
		} catch (AssertionError e) {
			logger.log(Status.ERROR, "Error");
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Status.ERROR, "Error");
			Assert.fail(e.getMessage());
		} finally {
			extent.flush();
		}
	}
}
