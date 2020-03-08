package com.automation.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.TestBase;

public class OrderHistoryPage extends TestBase {
	
	// Page factory - Object Repository
	
		@FindBy(className="account")
		@CacheLookup
		WebElement userNameLabel;

		@FindBy(partialLinkText="HISTORY")
		WebElement orders;
		
		@FindBy(partialLinkText="WISHLISTS")
		WebElement myWishlist;
		
		@FindBy(partialLinkText="ADDRESSES")
		WebElement address;

		@FindBy(partialLinkText="INFORMATION")
		WebElement info;
		
		@FindBy(partialLinkText="CREDIT")
		WebElement credits;
		
		@FindBy(xpath = "//table[@id='order-list']/tbody/tr[1]/td[6]/a[@title='Invoice']")
		WebElement firstInvoice;
		
		@FindBy(xpath = "//table[@id='order-list']/tbody/descendant::tr")
		WebElement orderHistoryTab;
		
		//Initializing the Page Objects:
		public OrderHistoryPage(){
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateOrderHistoryPageTitle(){
			return driver.getTitle();
		}
		
		public boolean verifyOrderHistoryExist(){
			return orders.isDisplayed();
		}
		
		public void showOrderHistory(){
			orders.click();
		}
		
		public boolean verifyWishListExist(){
			return myWishlist.isDisplayed();
		}
		
		public boolean verifyCreditSlipsExist(){
			return credits.isDisplayed();
		}
		
		public boolean verifyAddressExist(){
			return address.isDisplayed();
		}
		
		public boolean verifyMyInformationExist(){
			return info.isDisplayed();
		}
		
		public void getHistoricalInformation(){
			orders.click();
		}

		public void getUserInformation(){
			userNameLabel.click();
		}
		
		public void downloadFirstInvoice(){
			firstInvoice.click();
		}
		
		public List<WebElement> historyTableNotEmpty(){
			List<WebElement> resultEntries = driver.findElements(By.xpath("orderHistoryTab"));
			return resultEntries;
		}
		
}
