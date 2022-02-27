package com.qa.tutoninja.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.tutoninja.pages.AccountsPage;
import com.qa.tutoninja.pages.LoginPage;
import com.qa.tutoninja.pages.ProductInfoPage;
import com.qa.tutoninja.pages.RegisterPage;


public class BaseTest {

	public BasePage basepage;
	public Properties prop;
	public WebDriver driver;
	public AccountsPage accountsPage;
	public LoginPage loginpage;
	public RegisterPage registerpage;
	public ProductInfoPage productInfoPage;

	@Parameters({"browser", "version"})
	@BeforeTest
		public void setUp(String browserName , String browserVersion)
	{
		basepage=new BasePage();
		prop=basepage.initialize_Property();
		String browser=prop.getProperty("browser");
		if(browserName!=null )
		{
			browser=browserName;
		}
		
		driver=basepage.initializeDriver(browser,browserVersion);
		loginpage= new LoginPage(driver);
		driver.get(prop.getProperty("url"));
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
}
}
