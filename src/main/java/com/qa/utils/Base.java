package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	WebDriver driver;
	Properties config;
	
	public WebDriver initializeDriver() {
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir") + 
							"\\src\\main\\java\\com\\qa\\configuration\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = new Properties();
		try {
			config.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String browserName = config.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
									"\\src\\main\\java\\com\\qa\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + 
					"\\src\\main\\java\\com\\qa\\resources\\geckodriver.exe");
			
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		
		driver.get(config.getProperty("url"));
		return driver;
	}

}
