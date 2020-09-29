package testcases.ExtentReporting;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.utils.Base;

public class CoursesPageTest extends Base {
	
	WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		driver = initializeDriver();
	}
	
	@Test(priority=1)
	public void validateCoursesPageTitleTest() {

		driver.findElement(By.xpath("//a[text()='Courses']")).click();
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String title = driver.getTitle();
		Assert.assertEquals(title,"Rahul Shetty Academy1");
	}
	
	//@Test(priority=2)
	public void validateCoursesCount() {
		int noOfCourses = driver.findElements(By.xpath("//div[@class='course-listing']")).size();
		Assert.assertEquals(21, noOfCourses);
	}
	
	//@Test(priority=3)
	public void validateCourseTitles() {
		//List<WebElement> titlesList = driver.findElements(By.xpath("//div[@class='course-listing-title']"));
		//List<String> titlesTextList = titlesList.stream().map(ele->ele.getText()).collect(Collectors.toList());
	}

	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	
	

}
