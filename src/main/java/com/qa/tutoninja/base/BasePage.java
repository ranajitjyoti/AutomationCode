package com.qa.tutoninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.tutoninja.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	public OptionsManager optionsManager;
	public static String highlight;
	
	/**
	 * This method is user to initialize a driver 
	 * @param browser
	 * @param browserVersion 
	 * @return WebDriver driVer
	 */
	public WebDriver initializeDriver(String browser, String browserVersion)
	{
		highlight=prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);
		System.out.println("Browser value is :"+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				init_remoteDriver("chrome",browserVersion);
			}
						
			//driver=new ChromeDriver();
			else
			{
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));}

		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				init_remoteDriver("firefox",browserVersion);
			}
			//driver=new FirefoxDriver();
			else {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		}
		else if(browser.equalsIgnoreCase("safari"+browser)) {
			//driver=new SafariDriver();
			tlDriver.set(new ChromeDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser value : "+browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//driver.manage().window().fullscreen();
		
		return getDriver();
	}
	
	private void init_remoteDriver(String browser, String browserVersion) {
		if(browser.equals("chrome")) {
			DesiredCapabilities cap=DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion",browserVersion);
			cap.setCapability("screenResolution", "1280x720");
			cap.setCapability("screenWidth", "1500");
			cap.setCapability("screenHeight", "1080");
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
								e.printStackTrace();
			}
		
		}
		
		if(browser.equals("firefox")) {
			DesiredCapabilities cap=DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", "96.0");
			cap.setCapability("screenResolution", "1280x720");
			cap.setCapability("screenWidth", "1500");
			cap.setCapability("screenHeight", "1080");
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
								e.printStackTrace();
			}
		
		}
	}

	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();		
	}
	
	/**
	 * this method is used to load the file from config.properties file
	 * @return Properties
	 */
	public Properties initialize_Property()
	{
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/main/java/com/qa/tutoninja/config/config.properties");
			prop.load(ip);
			}
		 catch (FileNotFoundException e) {
				e.printStackTrace();
	}
		 catch (IOException e) {
				e.printStackTrace();
			}
	return prop;
	}
	/*
	 * This method is used to take screenshot
	 * it will return of the path of screenshot
	 */
	public String getScreenshot()
	{
		File src =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis();
		File destination=new File(path);
		try {
			FileUtils.copyFile(src, destination);
			}
	 catch (IOException e) {
		e.printStackTrace();
	}
		return path;
}
}
