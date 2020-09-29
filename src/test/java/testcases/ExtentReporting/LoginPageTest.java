package testcases.ExtentReporting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utils.Base;

public class LoginPageTest extends Base{
	WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = initializeDriver();
		//click on login
	}
	
	@Test(priority=1)
	public void validateLoginPageTitleTest() {
		driver.findElement(By.xpath("//div[@class='top-right clearfix']/div[2]/a")).click();
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = driver.getTitle();
		Assert.assertEquals(title,"Rahul Shetty Academy");
	}
	
	//@Test(priority=2,dataProvider="loginData")
	public void loginTest(String username,String password) {
		driver.findElement(By.id("user_email")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.xpath("//input[contains(@class,'login-button')]")).click();
	}
	
	
	@DataProvider
	public Object[][] loginData(){
		return new Object[][] {
			
				{"prathameshqa1990@gmail.com","Mtech123$"},
				{"prathameshd1990@yahoo.in","Mtech123$"},
				{"","password2"},
				{"username@gmail.com",""}
			
		};
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}


}
