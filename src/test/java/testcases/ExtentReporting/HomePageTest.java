package testcases.ExtentReporting;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.utils.Base;


public class HomePageTest extends Base{
	WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = initializeDriver();
	}
	
	@Test(priority=1)
	public void validateHomePageTitleTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title,"Rahul Shetty Academy");
	}
	
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
