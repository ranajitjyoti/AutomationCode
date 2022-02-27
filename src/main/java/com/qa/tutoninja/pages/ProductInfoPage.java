package com.qa.tutoninja.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.tutoninja.base.BasePage;
import com.qa.tutoninja.utils.ElementUtil;

public class ProductInfoPage extends BasePage {
	
		private WebDriver driver;
		private ElementUtil elementUtil;
		private By productNameHeader=By.xpath("//div[@id='content']//h1");
		private By productMetaData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]//li");
		private By productPrice=By.xpath("(//div[@class=\"col-sm-4\"]//ul[@class='list-unstyled'])[2]//h2");
		private By quantity=By.xpath("//input[@name='quantity']");
		private By addToCardButton=By.xpath("//button[@id='button-cart']");
		public By productImages=By.xpath("//ul[@class='thumbnails']//img");
		
		public ProductInfoPage(WebDriver driver)
		{
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}
		
		public Map<String,String> getProductInformation()
		{
			Map<String,String> productInfoMap=new HashMap<>();
			List<WebElement> productMataData=elementUtil.doGetElements(productMetaData);
			for(WebElement e:productMataData)
			{
				productInfoMap.put(e.getText().split(":")[0].trim(),e.getText().split(":")[1].trim());
			}
			String str=elementUtil.getElement(productPrice).getText();
			productInfoMap.put("Price", str);
			productInfoMap.put("Product Header Name", elementUtil.doGetText(productNameHeader));
			return productInfoMap;
		}
		
		public void selectQuantity(String quanty)
		{
			elementUtil.doSendKeys(quantity, quanty);
			
		}
		public void AddToCart() {
			elementUtil.doClick(addToCardButton);
		}
		public int getProductImages()
		{
			int size= elementUtil.doGetElements(productImages).size();
			System.out.println("Total images count : "+size);
			return size;
			}

		public String getProductInfoPageTitle(String productName) {
			
			return elementUtil.getTitleWaitforTitle(productName,5);
		}
		
}
