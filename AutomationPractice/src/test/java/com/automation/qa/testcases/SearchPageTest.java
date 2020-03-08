package com.automation.qa.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.SearchPage;
import com.automation.qa.util.TestUtil;

public class SearchPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	TestUtil testUtil;
	
	public SearchPageTest() {
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
		homePage = new HomePage();
		searchPage = new SearchPage(); //new instance created as landing page is search page result again.
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		searchPage = searchPage.enterTextInSearchBox(prop.getProperty("searchText"));
	}
	
	@Test(priority=1)
	public void verifyPageTitle(){
		String searchPageTitle = searchPage.validateTitlePage();
		Assert.assertEquals(searchPageTitle, "Search - My Store", "Search Page title not found");
	}
	
	@Test(priority=2)
	public void verifySearchBoxPresentTest(){
		Assert.assertTrue(searchPage.validateSearchBox(), "Search text box not displayed");
	}

	@Test(priority=3)
	public void verifyEnterSearchTextTest(){
		String temp = searchPage.validateSearchCriteria();
		assertTrue(temp.contains("DRESS"), "Text not entered for searching");
	}
	
	@Test(priority=4)
	public void verifySearchResultNotNullTest(){
		Assert.assertNotNull(searchPage.validateSearchResult(), "No search result displayed");
	}
	
	@Test(priority=5)
	public void verifyResultTextTest(){
		assertTrue(searchPage.validateResultText().matches("[0-9]{1} results have been found."), "No result found with search text");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}