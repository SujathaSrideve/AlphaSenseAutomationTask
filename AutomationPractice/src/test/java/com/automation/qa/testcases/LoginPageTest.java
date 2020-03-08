package com.automation.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.util.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		homePage = new HomePage();	
		loginPage = new LoginPage();
		testUtil = new TestUtil();
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "My Store","LoginPage title not matched");
	}

	@Test(priority=2)
	public void verifyLogoDisplayTest(){
		boolean flag = loginPage.validateLogoImage();
		Assert.assertTrue(flag, "Logo not visible in LoginPage");
	}
	
	@Test(priority=3)
	public void verifyLoginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(loginPage.validateLogout(), " Unsuccessful Login please check username and password");
	}
	
	@Test(priority=4) //This case added to capture screenshot when testcase is failing.
	public void verifyInvalidLoginAndCaptureScreenshotTest(){
		homePage = loginPage.login(prop.getProperty("InvalidUser"), prop.getProperty("password"));
		
		try {
			testUtil.takeScreenshotTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertFalse(loginPage.validateLogin(), " Unsuccessful Login please check username and password."
				+ "Screenshot captured for debugging purpose.");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
