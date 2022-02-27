package com.qa.tutoninja.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.tutoninja.base.BaseTest;
import com.qa.tutoninja.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic no 22135 : Registration & Sign Up Page")
@Story("Login Page functionality ")
public class LoginPageTest extends BaseTest{
	
	@Description("Veri fying the login page Title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void verifyLoginPageTitleTest()
	{
		String title=loginpage.getLoginPageTitle();
		System.out.println("Login page title is "+title);
		Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Verify forgot password Link")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority =2)
	public void verifyForgetpasswordLink() throws InterruptedException
	{
		boolean Forgot_Option=loginpage.ifForgotPassExist();
		Assert.assertTrue(Forgot_Option);
		System.out.println("Forgor Password Link Exists");  
		
	}
	
	@Description("Verify Login page Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void loginTest()
	{
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

}
