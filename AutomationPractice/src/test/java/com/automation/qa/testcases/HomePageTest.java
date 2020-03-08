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
//import com.automation.qa.pages.ShoppingCartPage;
import com.automation.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	LogoutPage logoutPage;
	SearchPage searchPage;

	public HomePageTest() {
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
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "My account - My Store");
	}
	
	@Test(priority=2)
	public void verifyUserAccountNameTest(){
		String labelName = homePage.verifyCorrectUserName();
		Assert.assertEquals(labelName, "Test Automation");
	}
	
	/* @Test(priority=3)
	public void verifyMyCartTest(){
		shoppingCartPage = homePage.validateMyCart();
	} */
	
	@Test(priority=3)
	public void verifyMyCartLinkTest(){
		Assert.assertTrue(homePage.validateMyCartLink());
	}
	
	@Test(priority=4)
	public void verifyLogoutLinkTest(){
		Assert.assertTrue(homePage.validateLogoutLink());
	}
	
	@Test(priority=5)
	public void verifyLoggingOutTest(){
		logoutPage = homePage.validateCanLogout();
	}
	
	@Test(priority=6)
	public void verifySearchTextTest(){
		Assert.assertTrue(homePage.validateSearchBox());
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
