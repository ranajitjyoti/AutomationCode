package com.qa.tutoninja.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.tutoninja.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void productInfoPageSetUp()
	{
		accountsPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));				
	}
	@Test
	public void productInfoPageTitleTest()
	{
		accountsPage.doSearch("iMac");
		productInfoPage =accountsPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"),"iMac");
		System.out.println(productInfoPage.getProductInfoPageTitle("iMac"));
	}	
	@Test(enabled=false)
	public void verifyProductInfoTest()
	{
		Assert.assertTrue(accountsPage.doSearch("MacBook"));
		productInfoPage =accountsPage.selectProductFromResults("MacBook");
		System.out.println(productInfoPage.getProductImages());
		Assert.assertTrue(productInfoPage.getProductImages()==5);
		Map<String,String>productInfoMap=productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		// Brand=Apple, Availability=In Stock, Product Header Name=MacBook, Price=$500.00, Product Code=Product 16, Reward Points=600
		Assert.assertEquals(productInfoMap.get("Product Header Name"),"MacBook");
		Assert.assertEquals(productInfoMap.get("Brand"),"Apple");
		Assert.assertEquals(productInfoMap.get("Price"),"$500.00");
		
	
	}
	
	

}
