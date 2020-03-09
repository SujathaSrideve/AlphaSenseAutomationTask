package com.automation.qa.testcases;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.TestBase;
import com.automation.qa.pages.HomePage;
import com.automation.qa.pages.LoginPage;
import com.automation.qa.pages.OrderHistoryPage;
import com.automation.qa.util.TestUtil;

public class OrderHistoryPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	OrderHistoryPage historyPage;
	
	public OrderHistoryPageTest() {
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
		historyPage = new OrderHistoryPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		historyPage.getUserInformation();
		
	}
	
	@Test(priority=1)
	public void verifyOrderHistoryExistTest(){
		Assert.assertTrue(historyPage.verifyOrderHistoryExist(),"Order History doesn't exist");
		Assert.assertTrue(historyPage.verifyWishListExist(), "MY WISHLISTS not found");
		Assert.assertTrue(historyPage.verifyAddressExist(), "MY ADDRESSES not found");
		Assert.assertTrue(historyPage.verifyMyInformationExist(), "MY INFORMATION not found");
		Assert.assertTrue(historyPage.verifyCreditSlipsExist(), "MY CREDITS not found");
	}

	@Test(priority=2)
	public void verifyOrderHistoryNotEmptyTest(){
		historyPage.getHistoricalInformation();
		Assert.assertNotNull(historyPage.historyTableNotEmpty(), "Order History Table is empty");
	}
	
	@Test(priority=3)
	public void verifyDownloadInvoiceTest() throws InterruptedException{
		
		historyPage.showOrderHistory();
		historyPage.downloadFirstInvoice();
	//wait for file download
		Thread.sleep(2000);
		 Assert.assertNotNull(folder.listFiles(), "Folder is empty and No file downloaded"); 
	} 
	
	@AfterMethod
	public void tearDown(){
		
	//Deleting all temp folders and file created for saving invoices
		for(File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
		
		driver.quit();
	}

}
