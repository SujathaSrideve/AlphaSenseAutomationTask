package com.automation.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// Page factory - Object Repository
	
	@FindBy(name= "email")
	WebElement username;
	
	@FindBy(name= "passwd")
	WebElement password;
	
	@FindBy(name= "SubmitLogin")
	WebElement loginBtn;
	
	@FindBy(className="login")
	WebElement signIn;
	
	@FindBy(id="header_logo")
	WebElement logo;
	
	@FindBy(className="login")
	WebElement login;
	
	@FindBy(className="logout")
	WebElement logout;
	
	//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
	//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validateLogoImage(){
			return logo.isDisplayed();
		}
		
		public boolean validateLogin(){
			return login.isDisplayed();
		}
		
		public boolean validateLogout(){
			return logout.isDisplayed();
		}
		
		public HomePage login(String un, String pwd){
			signIn.click();
			username.sendKeys(un);
			password.sendKeys(pwd);
			loginBtn.click();   	
			return new HomePage();
		}

}
