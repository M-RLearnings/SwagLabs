import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

class MainTest {
	

	@Test
	void test()throws InterruptedException {
		
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		WebDriver driver = new ChromeDriver();//declaration of an object
		driver.get("https://gmail.com");// open mail log in page
		
//		driver.findElement(By.id("identifierId")).sendKeys("test123",Keys.ENTER);
//		TimeUnit.SECONDS.sleep(10);
//		WebElement errorMessage= driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div/span/svg/path"));
//		Assert.assertEquals("Couldn't find your Google Account", errorMessage.getAttribute("innerHTML"));
		
		driver.findElement(By.id("identifierId")).sendKeys("ritup5812@gmail.com");//Write Email Id in EMail box and click next button using enter key
		driver.findElement(By.id("identifierNext")).click();//click Next Button using id and click command
		//driver.findElement(By.id("password")).sendKeys("Abcd1234$",Keys.ENTER);//using id but this is not working
		TimeUnit.SECONDS.sleep(5);//Wait for 5 Seconds to find next element
		
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("Abcd1234$",Keys.ENTER);//type password in password box and click next button using enter key
		//driver.findElement(By.id("passwordNext")).click();//click next button to get logged in using id and click command
		TimeUnit.SECONDS.sleep(10);
		
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div[1]/div[2]/div/a/img")).click();//open initial letter box right corner to check profile holder's name
		TimeUnit.SECONDS.sleep(15);
		WebElement resultText= driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[1]/div[2]/div[1]"));//define variable for actual value of assertion
		Assert.assertEquals("Ritu Patel",resultText.getAttribute("innerHTML"));// confidently compares name 
		TimeUnit.SECONDS.sleep(10);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div[1]/div[2]/div/a/img")).click();//close initial letter box
		TimeUnit.SECONDS.sleep(15);
		
		driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div/div")).click();//open compose window/box to write an Email
		TimeUnit.SECONDS.sleep(10);
		driver.findElement(By.name("to")).sendKeys("het09it@gmail.com");//writes recipient's email address
		TimeUnit.SECONDS.sleep(10);
		driver.findElement(By.name("subjectbox")).sendKeys("Hello");// writes subject of an email
		
		//TimeUnit.SECONDS.sleep(10);
		//driver.findElement(By.name("subjectbox")).sendKeys(Keys.TAB);//to go to body box
		//TimeUnit.SECONDS.sleep(2);
		//driver.findElement(By.xpath("//*[@aria-label='Message Body']")).sendKeys("Test Email");// write email in body
		
		TimeUnit.SECONDS.sleep(15);
		driver.findElement(By.xpath("//*[@role='button' and contains(text(),'Send')]")).click();//clicks send button to send an email 
		TimeUnit.SECONDS.sleep(5);
		driver.quit();
		
	
	}

}
