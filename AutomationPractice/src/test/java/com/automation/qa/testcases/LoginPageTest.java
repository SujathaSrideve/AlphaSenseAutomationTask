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
		testUtil = new TestUtil();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "My Store");
	}
	
	@Test(priority=2)
	public void verifyLogoDisplayTest(){
		boolean flag = loginPage.validateLogoImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void verifyLoginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	} 
	
	@Test(priority=4)
	public void verifyInvalidLoginAndCaptureScreenshotTest(){
		homePage = loginPage.login(prop.getProperty("InvalidUser"), prop.getProperty("password"));
		try {
			testUtil.takeScreenshotTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
