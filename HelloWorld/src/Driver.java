import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;

public class Driver{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		WebDriver driver = new ChromeDriver();//declaration of an object
		driver.get("https://gmail.com");// open mail log in page
		driver.findElement(By.id("identifierId")).sendKeys("ritup5812@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		//driver.findElement(By.id("password")).sendKeys("Abcd1234$");
		TimeUnit.SECONDS.sleep(5);
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("Abcd1234$");
		driver.findElement(By.id("passwordNext")).click();
		TimeUnit.SECONDS.sleep(15);
		
		driver.quit();
		
	}
	
}
