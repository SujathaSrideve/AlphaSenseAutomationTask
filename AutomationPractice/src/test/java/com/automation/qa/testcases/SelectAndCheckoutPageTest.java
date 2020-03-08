package com.automation.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.SearchPage;
import com.automation.qa.pages.SelectAndCheckoutPage;

public class SelectAndCheckoutPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	SelectAndCheckoutPage checkoutPage;
	

	public SelectAndCheckoutPageTest() {
		super();
	}

	//test cases run are independent of each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		searchPage = new SearchPage();
		checkoutPage = new SelectAndCheckoutPage();//new object instantiated to represent final checkout
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		searchPage = searchPage.enterTextInSearchBox(prop.getProperty("searchText"));
		checkoutPage = checkoutPage.addToProceed();
	}
	
	@Test(priority=1)
	public void verifyOrderPageTitleTest(){
		String orderPageTitle = checkoutPage.getTitlePage();
		Assert.assertEquals(orderPageTitle, "Order - My Store", "Order Page title not matched");
	}
	
	@Test(priority=2)
	public void validateSelectedItemTest(){
		String temp = checkoutPage.validateSelectedItem();
		Assert.assertEquals(temp, "Printed Chiffon Dress", "Selected dress not found");
	}

	@Test(priority=3)
	public void verifyAddressPresentTest(){
		Assert.assertTrue(checkoutPage.addressDisplayed(),"Address for dispatch not displayed");
	} 

	@Test(priority=4)
	public void verifyConfirmationMessageTest(){
		checkoutPage.proceedToNext();
		String temp = checkoutPage.validateConfirmationMessage();
		Assert.assertEquals(temp, "ORDER CONFIRMATION", "Order not placed successfully. Please check");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
