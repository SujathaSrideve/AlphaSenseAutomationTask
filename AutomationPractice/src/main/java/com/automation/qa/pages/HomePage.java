package com.automation.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.TestBase;

public class HomePage extends TestBase{
	
	// Page factory - Object Repository
	
	@FindBy(xpath = "//a[@class='account']/span")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(name= "search_query")
	WebElement searchBox;
	
	@FindBy(name= "img-responsive")
	WebElement homeLogo;
	
	@FindBy(xpath= "//a[@class='logout']")
	WebElement logoutLink;
	
	@FindBy(xpath= "//a[@title='View my shopping cart']")
	WebElement myCart;
	
	//Initializing the Page Objects:
			public HomePage(){
				PageFactory.initElements(driver, this);
			}
			
	//Actions:
			public String validateHomePageTitle(){
				return driver.getTitle();
			}
			
			public String verifyCorrectUserName(){
				return userNameLabel.getText();
			}
	
			public boolean validateSearchBox(){
				return searchBox.isDisplayed();
			}
			
			public boolean validateLogoutLink(){
				return logoutLink.isDisplayed();
			}
			
			public boolean validateMyCartLink(){
				return myCart.isDisplayed();
			}
			
			/*public ShoppingCartPage validateMyCart(){
				myCart.click();
				return new ShoppingCartPage();
			}*/
			
			public LogoutPage validateCanLogout(){
				logoutLink.click();
				return new LogoutPage();
			}
			
			 public SearchPage enterSearchText(String st){
				searchBox.sendKeys(st);
				searchBox.sendKeys(Keys.ENTER);   	
				return new SearchPage();
			}
			
			
}
