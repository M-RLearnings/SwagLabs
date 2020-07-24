import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

class LoginTest {

	@Test
	void test() throws InterruptedException {
		
		WebDriver chrome = new ChromeDriver();
		chrome.get("https://www.facebook.com/");
		TimeUnit.SECONDS.sleep(5);
		chrome.findElement(By.id("email")).sendKeys("test124444443@yahoo.com",Keys.TAB);
		chrome.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("abcd4321!",Keys.ENTER);
		//TimeUnit.SECONDS.sleep(15);
		//chrome.findElement(By.xpath("//*[@id=\"u_0_b\"]")).click();//login button 
		TimeUnit.SECONDS.sleep(30);
		WebElement errorText= chrome.findElement(By.xpath("//*[@role=\"alert\"]"));
		Assert.assertEquals("The email you’ve entered doesn’t match any account. <a href=\"/r.php\">Sign up for an account.</a>", errorText.getAttribute("innerHTML"));
		TimeUnit.SECONDS.sleep(10);
		chrome.quit();
		
		}

}
