package com.qa.tutoninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.tutoninja.base.BasePage;
import com.qa.tutoninja.utils.Constants;
import com.qa.tutoninja.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elementUtil;
	//1. By locator is object repository 
	
	private By userNameField=By.xpath("//input[@id=\'input-email\']");
	private By passwordField=By.xpath("//input[@id='input-password']");
	private By submitButton=By.xpath("//input[@type='submit' and @ value='Login']");
	private By forgotPasswordOption=By.linkText("Forgotten Password");
	private By  registerLink=By.linkText("Register");
//	private By userNameField=By.xpath("//input[@id='username']");
//	private By passwordField=By.xpath("//input[@id='password']");
//	private By submitButton=By.xpath("//input[@id='btnLogin']");
//	private By forgotPasswordOption=By.linkText("Forgot password?");
	
	
	
	// Constructor of page class 

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	// page actions : this is feature & behavior of the page
	@Step("Getting login page title.....")
	 public String getLoginPageTitle()
	 {
		 return elementUtil.getTitleWaitforTitle(Constants.LOGIN_PAGE_TITLE, 10);
	 }
	@Step("Checking  Forgot password  page exists.....")
	 public boolean ifForgotPassExist() throws InterruptedException
	 {
		// driver.findElement(forgotPasswordOption).isDisplayed();
		Thread.sleep(10000);
		 return elementUtil.doIsDisplayed(forgotPasswordOption);
	 }
	 
	 public AccountsPage doLogin(String un, String pass)
	 {
		System.out.println("Login with user name : "+un+" and password :"+pass);
		elementUtil.doSendKeys(userNameField, un);
		elementUtil.doSendKeys(passwordField, pass);
		elementUtil.doClick(submitButton);
		return new AccountsPage(driver);
		
	 }
//	 public RegisterPage navigateToRagisterPage()
//	 {
//		 elementUtil.doClick(registerLink);
//		 return new RegisterPage(driver);
//	 }

}
