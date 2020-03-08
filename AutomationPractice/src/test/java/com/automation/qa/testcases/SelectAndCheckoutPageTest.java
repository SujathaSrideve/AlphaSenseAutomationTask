package com.automation.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.LogoutPage;
import com.automation.qa.pages.SearchPage;
import com.automation.qa.pages.SelectAndCheckoutPage;
import com.automation.qa.util.TestUtil;

public class SelectAndCheckoutPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	LogoutPage logoutPage;
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
		testUtil = new TestUtil();
		searchPage = new SearchPage();
		checkoutPage = new SelectAndCheckoutPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		searchPage = searchPage.enterTextInSearchBox(prop.getProperty("searchText"));
		checkoutPage = checkoutPage.addToProceed();
	}
	
	@Test(priority=1)
	public void verifyOrderPageTitleTest(){
		String orderPageTitle = checkoutPage.getTitlePage();
		Assert.assertEquals(orderPageTitle, "Order - My Store");
	}
	
	@Test(priority=2)
	public void validateSelectedItemTest(){
		String temp = checkoutPage.validateSelectedItem();
		Assert.assertEquals(temp, "Printed Chiffon Dress");
	}

	@Test(priority=3)
	public void verifyAddressPresentTest(){
		Assert.assertTrue(checkoutPage.addressDisplayed());
	} 
	
	@Test(priority=4)
	public void verifyConfirmationMessageTest(){
		checkoutPage.proceedToNext();
		String temp = checkoutPage.validateConfirmationMessage();
		Assert.assertEquals(temp, "ORDER CONFIRMATION");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
