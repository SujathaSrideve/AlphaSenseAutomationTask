package com.automation.qa.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.TestBase;

public class SearchPage extends TestBase{
	
	// Page factory - Object Repository
	
	@FindBy(name= "search_query")
	WebElement searchBox;
	
	@FindBy(className= "product-container")
	WebElement searchResult;
	
	@FindBy(className= "lighter")
	WebElement searchedKeyword;

	@FindBy(className= "heading-counter")
	WebElement resultText;

	public SearchPage(){
		PageFactory.initElements(driver, this);
		
	}
	
	//actions
	
	public String validateSearchCriteria(){
		return searchedKeyword.getText();
	}
	
	public String validateTitlePage(){
		return driver.getTitle();
	}
	
	public boolean validateSearchBox(){
		return searchBox.isDisplayed();
	}
	
	public String validateResultText(){
		return (resultText.getText());
	}
	
	public List<WebElement> validateSearchResult(){
		List<WebElement> resultEntries = driver.findElements(By.xpath("searchResult"));
		return resultEntries;
	}
	
	public SearchPage enterTextInSearchBox(String St){
		searchBox.sendKeys(St);
		searchBox.sendKeys(Keys.ENTER);
		return new SearchPage();
	}
}