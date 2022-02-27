package com.qa.tutoninja.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.tutoninja.base.BasePage;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(this.driver);
		
		
	}
	
	public   WebElement getElement(By locator)
	{
		WebElement element =driver.findElement(locator);
		if(BasePage.highlight.equals("true"))
		{
			jsUtil.flash(element);
		}
		return element;
	}
	public   void doSendKeys(By locator,String value) {
		getElement(locator).sendKeys(value);
	}
	public   void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public   By doGetLocator(String value) {
		return By.name(value);
	}
	
	public  int getWebelement(String tagName) {
		return driver.findElements(By.tagName(tagName)).size();
		
	}
	public void totalAttributest(String tagName,String attributeName) {
		List<WebElement> totalAttributes=driver.findElements(By.tagName(tagName));
		for(int i=0;i<getWebelement(tagName);i++)
		{
			String attributelink=totalAttributes.get(i).getAttribute(attributeName);
			//System.out.println(i+"------>"+attributelink);
		}
	}
	public  void doClickFronthelist(By locators,String listName) {
		List<WebElement> LanguageList=driver.findElements(locators);
		
		for(int i=0;i<LanguageList.size();i++)
		{
			String atr=LanguageList.get(i).getText();
			System.out.println(atr);
			if(atr.equals(listName))
			{
				LanguageList.get(i).click();
				break;
			}
		}}
	// ******************************** select dropdown  method*********************************
	public  void doSelectTextbyVisibleText(By locators,String text) {
		WebElement element=getElement(locators);
		Select obj=new Select(element);
		obj.selectByVisibleText(text);
	}
	public  void doSelectTextbyValueText(By locators,String text) {
		WebElement element=getElement(locators);
		Select obj=new Select(element);
		obj.selectByValue(text);
	}
	//********************************Action Class Methods*************************************

	public  void doActionSendKeys(By locator,String value)
	{
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
	}
	public  void doActionClick(By locator)
	{
		Actions act=new Actions(driver);
	
		act.click(getElement(locator)).perform();
	}
//**********************************wait util****************************************
	public  WebElement waitforElemenToLocated(By locators, int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locators)); 
		
		
	}
	public  String getTitleWaitforTitle(String value, int time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.titleContains(value));
		return driver.getTitle();
	}
	public  Alert alertWaitMethod(int time)
	{
		WebDriverWait wait =new WebDriverWait(driver,10);
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	public  boolean waitForUrl(int time, String value)
	{
		WebDriverWait wait =new WebDriverWait(driver,time);
		 return wait.until(ExpectedConditions.urlContains(value));
	}
	public  List<WebElement> visibilityOfByLocators(By locators, int time)
	{
		WebDriverWait wait=new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locators));
				
	}
	public  void getPageLinkText(By locators, int time)
	{
		List<WebElement> elements=visibilityOfByLocators(locators,time);
System.out.println(elements.size());
		
		for(WebElement e:elements)
		{
			System.out.println(e.getText());
		}
		
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public String doGetText(By locator)
	{
		return driver.findElement(locator).getText();
	}
	
	public List<WebElement> doGetElements(By locator)
	{
		return driver.findElements(locator);
	}
// ********************************* FLUENT WAIT*************************************************
	public  WebElement waitForElementWithFluentWait(By locator,int timeOut, int pollingTime )
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
