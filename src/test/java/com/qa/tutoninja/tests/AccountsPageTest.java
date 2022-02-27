package com.qa.tutoninja.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.tutoninja.base.BaseTest;
import com.qa.tutoninja.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest{
	@BeforeClass
	public void accountsPageSetUp()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountsPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));		
	}
	@Description("Verify Account page title Test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority=1, enabled=true)
	public void accountsPageTitleTest()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title=accountsPage.getAccountPageTitle();
		System.out.println("My Account page Title is : "+title);
		Assert.assertEquals(title,Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Verify Account page Heading title Test")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=2)
	public void verifyAccountPageHeaderTest()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String headerValue=accountsPage.getHeaderValue();
		System.out.println("HomePage Header is "+headerValue);
		Assert.assertEquals(headerValue,Constants.ACCOUNT_PAGE_HEADER);
			
	}
	@Description("Verify number of items available Test")
	@Severity(SeverityLevel.TRIVIAL)
	@Test (priority=3)
	public void verifyMyAccountSectionCountTest()
	{
		int count=accountsPage.getAccountScetionCount();
		Assert.assertTrue(count==Constants.ACCOUNT_SECTIONS_COUNT);
		System.out.println("Number of section present = "+count);
	}
	
	@Description("Verify AccountScetion headerst")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=4)
	public void verifyMyAccountSectionListTest()
	{
		accountsPage.getAccountScetionHeaders();
		Assert.assertEquals(accountsPage.getAccountScetionHeaders(), Constants.getAccountsSectionsList());

	}
	
	@Description("Verify Search text available or not ")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority=5)
	public void searchText()
	{
		Assert.assertTrue(accountsPage.doSearch("mac"));

}
}
