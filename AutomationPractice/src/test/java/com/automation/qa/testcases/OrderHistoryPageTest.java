package com.automation.qa.testcases;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
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
	File folder;
	
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
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		historyPage = new OrderHistoryPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		historyPage.getUserInformation();
		
		//creating unique folder
		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();
		
		//Chrome Options
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> perfs = new HashMap<String, Object>();
		
		//popup disabled and capability set.
		perfs.put("profile.default_content_settings.popups", 0);
		perfs.put("download.default_directory", folder.getAbsolutePath());
		options.setExperimentalOption("prefs", perfs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cap);
		driver = new ChromeDriver(options);
	}

	@Test(priority=1)
	public void verifyOrderHistoryExistTest(){
		Assert.assertTrue(historyPage.verifyOrderHistoryExist());
	}
	
	@Test(priority=2)
	public void verifyMyWishlistExistTest(){
		Assert.assertTrue(historyPage.verifyWishListExist());
	}
	
	@Test(priority=3)
	public void verifyAddressExistTest(){
		Assert.assertTrue(historyPage.verifyAddressExist());
	}
	
	@Test(priority=4)
	public void verifyMyInformationExistTest(){
		Assert.assertTrue(historyPage.verifyMyInformationExist());
	}
	
	@Test(priority=5)
	public void verifyOrderHistoryNotEmptyTest(){
		Assert.assertNotNull(historyPage.historyTableNotEmpty());
	}
	@Test(priority=6)
	public void verifyPageTitleTest(){
		String historyPageTitle = historyPage.validateOrderHistoryPageTitle();
		Assert.assertEquals(historyPageTitle, "Order history - My Store");
	} 
	
	@Test(priority=7)
	public void verifyDownloadInvoiceTest() throws InterruptedException{
		 historyPage.showOrderHistory();
		 historyPage.downloadFirstInvoice();
		 
		 //wait for download
		 Thread.sleep(2000);
		 File listofFiles[] = folder.listFiles();
		 //make sure directory not empty
		 Assert.assertTrue(listofFiles.length > 0);
	}
	@Test(priority=8)
	public void verifyDownloadInvoiceNotEmptyTest() throws InterruptedException{
		 historyPage.showOrderHistory();
		 historyPage.downloadFirstInvoice();
		 
		 //wait for download
		 Thread.sleep(2000);
		 File listofFiles[] = folder.listFiles();
		 //make sure downloaded file not empty
		 for(File file : listofFiles )
		 Assert.assertTrue(file.length() > 0);
		 
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
		for(File file : folder.listFiles()) {
			file.delete();
		}
		folder.delete();
	}
}
