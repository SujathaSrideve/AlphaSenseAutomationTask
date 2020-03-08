package com.automation.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.SearchPage;
import com.automation.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
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
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "My account - My Store", "HomePage title not matched");
	}
	@Test(priority=2)
	public void verifyUserAccountNameTest(){
		String labelName = homePage.verifyCorrectUserName();
		Assert.assertEquals(labelName, "Test Automation", "Username not displayed in label");
	}
	@Test(priority=3)
	public void verifyMyCartLinkAndSearchBoxTest(){
		Assert.assertTrue(homePage.validateMyCartLink(), "CartLink not displayed in page");
		Assert.assertTrue(homePage.validateSearchBox(), "SearchBox not displayed in page");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
