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
	
	@FindBy(xpath= "//a[@class='login']")
	WebElement SignIn;
	
	@FindBy(xpath= "//img[@class='img-responsive']")
	WebElement Logo;
	
	//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
	//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validateLogoImage(){
			return Logo.isDisplayed();
		}
		
		public HomePage login(String un, String pwd){
			SignIn.click();
			username.sendKeys(un);
			password.sendKeys(pwd);
			loginBtn.click();   	
			return new HomePage();
		}

}
