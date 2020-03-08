package com.automation.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.qa.base.TestBase;

public class SelectAndCheckoutPage extends TestBase {
	
	// Page factory - Object Repository
	
	@FindBy(className = "heading-counter")
	WebElement checkoutMsg;
	
	@FindBy(xpath= "//h5/a[@title='Printed Chiffon Dress']")
	WebElement selectItem;
	
	@FindBy(xpath= "//button[@type='submit']/span[contains(text(), 'Add to cart')]")
	WebElement addToCart;
	
	@FindBy(xpath= "//a[@title='Proceed to checkout']")
	WebElement checkout;
	
	@FindBy(xpath= "//td/p[@class='product-name']/a")
	WebElement itemSelected;

	@FindBy(className = "shopping_cart")
	WebElement shoppingCart;

	@FindBy(className = "address_alias")
	WebElement address;
	
	@FindBy(xpath= "//a[@class='button btn btn-default standard-checkout button-medium']/span")
	WebElement next1;
	
	@FindBy(xpath= "//span[text()='Proceed to checkout']")
	WebElement next2;
	
	@FindBy(xpath= "//button[@name='processCarrier']/span")
	WebElement next3;

	@FindBy(id="cgv")
	WebElement checkbox; 
	
	@FindBy(className= "bankwire")
	WebElement payment;
	
	@FindBy(xpath= "//button[@class='button btn btn-default button-medium']")
	WebElement agree;

	@FindBy(className= "page-heading")
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
		WebDriverWait wait = new WebDriverWait(driver, 20);
		 try 
	        {
			 	next1.click();  
	            wait.until(ExpectedConditions.elementToBeClickable(next2));
	            next2.click();
	            checkbox.click();
	            wait.until(ExpectedConditions.elementToBeClickable(next3));
	            next3.click();
	            wait.until(ExpectedConditions.elementToBeClickable(payment));
	            payment.click();
	            wait.until(ExpectedConditions.elementToBeClickable(agree));
	            agree.click();
	        } 
	        catch (Exception e) {
	        }

	}
	
	public String validateConfirmationMessage(){
		return confirm.getText();
	}
}
