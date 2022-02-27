package com.qa.tutoninja.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.tutoninja.base.BasePage;
import com.qa.tutoninja.utils.Constants;
import com.qa.tutoninja.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Epic no 22136 : Design Account Page")
@Story("User story of Design & Functionality of Accountpage Item selection  ")
public class AccountsPage extends BasePage 
{
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header=By.xpath("//div[@id='logo']//a[contains(text(),'Your Store')]");
	private By accountScetionHeaders=By.xpath("//div[@id='content']/h2");
	private By searchText=By.xpath("//input[@placeholder='Search']");
	private By searchButton=By.xpath("//div[@id='search']//button[@type='button']");
	private By searchItemResults=By.xpath("//div[@class='row']//div[@class='product-thumb']");
	private By resultItems=By.xpath("//div[@class='row']//div[@class='product-thumb']//div[@class='caption']//a");
	
	

	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	@Step("Getting login page title.....")
	public String  getAccountPageTitle()
	{
		return elementUtil.getTitleWaitforTitle(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}
	@Step("Getting login Account page Heaer.....")
	public String getHeaderValue()
	{
		if(elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}
	@Step("Getting  number of account section .....")
	public int getAccountScetionCount() {
		//return driver.findElements(accountScetionHeaders).size();
		return elementUtil.doGetElements(accountScetionHeaders).size();
	}
	public List<String> getAccountScetionHeaders() {
		List<String> accountList=new ArrayList<>();
		List<WebElement> AccountSceLists=driver.findElements(accountScetionHeaders);
		for(WebElement e :AccountSceLists )
		{
			accountList.add(e.getText());
		}
		return accountList;
	}
	
	@Step("Scerching a product : {0}.....")
	public boolean doSearch(String productName)
	{
//		driver.findElement(searchText).sendKeys(productName);
//		driver.findElement(searchButton).click();
		elementUtil.doSendKeys(searchText, productName);
		elementUtil.doClick(searchButton);
		if(elementUtil.doGetElements(accountScetionHeaders).size()>0)
		{
			return true;
		}
		return false;
	}
	@Step("Selecting  a product : {0}.....")
	public ProductInfoPage selectProductFromResults(String productName )
	{
		List<WebElement> resultsItems=elementUtil.doGetElements(resultItems);
		System.out.println("Number of items Displayed   "+resultsItems.size());
		
		for(WebElement e: resultsItems)
		{
			if(e.getText().equals(productName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
		

}
