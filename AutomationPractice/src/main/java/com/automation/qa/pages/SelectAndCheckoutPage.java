package com.automation.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.TestBase;
import com.automation.qa.util.TestUtil;

public class SelectAndCheckoutPage extends TestBase {
	
	// Page factory - Object Repository
	
	@FindBy(xpath= "//h5/a[@title='Printed Chiffon Dress']")
	WebElement selectItem;
	    
	
	@FindBy(xpath= "//button[@type='submit']/span[contains(text(), 'Add to cart')]")
	WebElement addToCart;
	
	@FindBy(xpath= "//a[@title='Proceed to checkout']")
	WebElement checkout;
	
	@FindBy(xpath= "//span[@title='Close window']")
	WebElement close;
	
	@FindBy(xpath= "//span[@class='heading-counter']")
	WebElement checkoutMsg;
	
	@FindBy(xpath= "//td/p[@class='product-name']/a")
	WebElement itemSelected;
	
	@FindBy(xpath= "//div[@class='shopping_cart']/a")
	WebElement shoppingCart;
	
	@FindBy(xpath= "//span[@class='address_alias']")
	WebElement address;
	
	@FindBy(xpath= "//a[@class='button btn btn-default standard-checkout button-medium']/span")
	WebElement next1;
	
	@FindBy(xpath= "//span[text()='Proceed to checkout']")
	WebElement next2;
	
	/*@FindBy(xpath= "//button[@type='submit']/span")
	WebElement next3;*/
	
	@FindBy(xpath= "//button[@name='processCarrier']/span")
	WebElement next3;
	
	@FindBy(xpath= "//span/input[@type='checkbox']")
	WebElement checkbox;
	
	@FindBy(xpath= "//a[@class='bankwire']")
	WebElement payment;
	
	@FindBy(xpath= "//button[@class='button btn btn-default button-medium']")
	WebElement agree;
	
	@FindBy(xpath= "//h1[@class='page-heading']")
	WebElement confirm;
	
	
	//Initializing the Page Objects:
	public SelectAndCheckoutPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String getTitlePage(){
		return driver.getTitle();
	}
	
	public String productAdded(){
		return checkoutMsg.getText();
	}
	
	public SelectAndCheckoutPage addToProceed(){
		selectItem.click();
		addToCart.click();
		checkout.click();
		return new SelectAndCheckoutPage();
	}
	
	public void shoppingCart(){
		driver.switchTo().defaultContent();
		shoppingCart.click();
	}
	
	public String itemVisibleInOrderPage(){
		return itemSelected.getText();
	}
	
	public boolean addressDisplayed(){
		return address.isDisplayed();
	}
	
	public String validateSelectedItem(){
		return itemSelected.getText();
	}
	
	public void proceedToNext(){
		next1.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
		next2.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
		checkbox.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
		next3.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
		payment.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
		agree.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_OPTIONS, TimeUnit.SECONDS);
	}
	
	public String validateConfirmationMessage(){
		return confirm.getText();
	}
}
