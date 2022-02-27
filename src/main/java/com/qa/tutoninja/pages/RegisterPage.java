package com.qa.tutoninja.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.tutoninja.base.BasePage;
import com.qa.tutoninja.utils.ElementUtil;

public class RegisterPage extends BasePage {
	ElementUtil elementUtil;
	private By firstName=By.xpath("//input[@id='input-firstname']");
	private By lastName=By.xpath("//input[@id='input-lastname']");
	private By email_Id=By.xpath("//input[@id='input-email']");
	private By telephoe=By.xpath("//input[@id='input-telephone']");
	private By password=By.xpath("//input[@id='input-password']");
	private By confirmPassword=By.xpath("//input[@id='input-confirm']");
	private By subscribeYes=By.xpath("//div[@class='col-sm-10']/label[@class='radio-inline' ]/input[@value='1']");
	private By subscribeNo=By.xpath("//div[@class='col-sm-10']/label[@class='radio-inline' ]/input[@value='0']");
	private By agreeCheckBox=By.xpath("//input[@type='checkbox']");
	private By continueButton=By.xpath("//input[@type='submit']");
	private By accountCreateSuccessMessage=By.xpath("//div[@id='content']/h1");
	private By logoutLink=By.linkText("Logout");
	private By  registerLink=By.linkText("Register");
	
	public  RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	public boolean accountRegistration(String firstname, String lastName, String emailId, String telephone, String password, String subscribe)
	{
		elementUtil.doSendKeys(this.firstName,firstname);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email_Id, emailId);
		elementUtil.doSendKeys(this.telephoe, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		if(subscribe.equals("Yes"))
		{
			elementUtil.doClick(subscribeYes);
		}
		else
		{
			elementUtil.doClick(subscribeNo);	
		}
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);
		String text=elementUtil.doGetText(accountCreateSuccessMessage);
		System.out.println(text);
		if(text.contains("Your Account Has Been Created"))
		{
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
