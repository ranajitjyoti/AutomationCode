package com.qa.tutoninja.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.tutoninja.base.BaseTest;
import com.qa.tutoninja.testlisteners.ExtentReportListener;
import com.qa.tutoninja.utils.ExcelUtil;
@Listeners(ExtentReportListener.class)
public class registerPageTest extends BaseTest {
	@BeforeClass
	public void registerPageSetUp()
	{
		registerpage=loginpage.navigateToRagisterPage();
	}
	@DataProvider
	public Object[][] getRegisterData() throws InvalidFormatException, IOException
	{
		Object data[][]=ExcelUtil.getTestData("Registration");
		return data;
	}
	
	@Test(dataProvider="getRegisterData")
	public void userRegistrationTest(String firstName,String lastName,String email_Id, String telephone, String password, String subscribe)
	{
		registerpage.accountRegistration( firstName, lastName, email_Id,  telephone,  password,  subscribe);
	}
	
	

}
