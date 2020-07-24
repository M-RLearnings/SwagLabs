import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class LogInTest {
	//Declaration of an Object 
	WebDriver chrome=new ChromeDriver();
	WebDriverWait wait= new WebDriverWait(chrome,30);
	double itemprice;
	double itemTotalPrice;
	int num= randnum(4,1);
	int addnum= randnum(6,1); //2
	int addnum2 = getNewNumber(addnum,randnum(6,1)); //getNewNumber(2,2)3
	int infoNum= randnum(5,0);
	
	@Test
	public void logInTest() throws InterruptedException {
		setupDriver();
		logIn();
		//itemInfo(infoNum);
		//sortItems();
		addtoCart(addnum);
		continueShop();
		addtoCart(addnum2);
		shopCart(); 
		checkOut();
		sideBar();
		//quitBrowser();
		 
	}
	
	public int getNewNumber(int numberOne, int numberTwo) {
		
		while(numberOne == numberTwo) { //jya sudhi aa true che tya sudhi loop ma aavse
			
			numberTwo=randnum(6,1);//2,2,3
			
			if(numberOne!=numberTwo) {
				break;
			}
		}
		return numberTwo;
	}
	
	public void setupDriver() throws InterruptedException {
		//Open sauce demo Website
		chrome.get("https://www.saucedemo.com/index.html");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"login_credentials\"]")));
			
	}
	
	
	public void logIn() throws InterruptedException {
		
		 // define variable for user name array and call method randnum 
		
		//find element of user name from html page of website
		WebElement username= chrome.findElement(By.xpath("//*[@class=\"login_credentials\"]"));
		
		//assign string array to user name texts
		String[] lines = username.getText().split("\\n");
		
		//input user name from array and press tab
		chrome.findElement(By.id("user-name")).clear();
		chrome.findElement(By.id("user-name")).sendKeys(lines[num],Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div[2]")));
		
		//find element of password from html page of website
		WebElement password= chrome.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]"));
		
		//assign string array to password texts
		String[] passlines= password.getText().split("\\n");
		
		//input password to password box from array and press enter
		chrome.findElement(By.id("password")).clear();
		chrome.findElement(By.id("password")).sendKeys(passlines[1],Keys.ENTER);	
				
		switch(num) {
		
		case 1:	
			Assert.assertTrue(chrome.findElement(By.className("product_label")).isDisplayed());
			break;
			
		case 2:
			WebElement errmsg= chrome.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3"));//find element of error text from html page of website using xpath 
			Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", errmsg.getText());//validate log in, if fails error msg will pop up below password box	 
			while(num==2) {
				num=randnum(4,1);
				if(num!=2) {
					break;
				}
			}
			logIn();
			break;
			
		case 3:
			chrome.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
			Assert.assertTrue(chrome.findElement(By.id("logout_sidebar_link")).isDisplayed());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu_button_container\"]/div/div[1]")));
			chrome.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[1]")).click();
			break;
			
		case 4:
			Assert.assertTrue(chrome.findElement(By.id("shopping_cart_container")).isDisplayed());
			break;
		}
	}
	
	
	public int randnum(int max, int min) {
		
		int a= (int) (Math.random()*(max-min+1)+min);
		return a;
		}
	
	
	public void quitBrowser() {
		
		chrome.quit();
		}
	
	
	public void addtoCart(int number) throws InterruptedException {
		
		addItem(number);
		removeItem(number);
		int num2=randnum(6,1);
		System.out.println("second time adding item(num2):"+num2);
		addItem(num2);
		
		
		//price 
		switch(num2) {
			
		case 1: 
			getPriceItem1(num2);
			System.out.println("itemprice1:"+itemprice);
			break;
			
		case 2:
			//remove item2 from cart
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")));
			chrome.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
			while(num2==2) {
				num2= randnum(6,1);
				if(num2!=2) {
					break;
				}
			}
			System.out.println("number generated in case 2:"+num2);
			addItem(num2);
			getItemPrice(num2);
			System.out.println("itemprice2:"+itemprice);
			break;
		
		default:
			getItemPrice(num2);
			System.out.println("itemprice:"+itemprice);
			
		    }
		
	    
		itemTotalPrice = itemTotalPrice + itemprice;
		System.out.println("itemTotalPrice:"+itemTotalPrice);
		
		//number of items in cart while shopping
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")));
		WebElement numofitems= chrome.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
		WebElement counter= chrome.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
		Assert.assertEquals(counter.getText(), numofitems.getText());
		
	}
	
	public void addItem(int itemNum) throws InterruptedException {
		
		//add to cart
		String addbutton= "(//*[@id=\"inventory_container\"]/div/div["+itemNum+"]/div[3]/button)";
		System.out.println("additem:"+addbutton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addbutton)));
		chrome.findElement(By.xpath(addbutton)).click();
		}
	
	public void removeItem(int itemNum) {
		//remove an item from cart
		String removebutton= "(//*[@id=\"inventory_container\"]/div/div["+itemNum+"]/div[3]/button)";
		System.out.println("removebutton:"+removebutton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(removebutton)));
		chrome.findElement(By.xpath(removebutton)).click();
		Assert.assertTrue(chrome.findElement(By.xpath(removebutton)).isDisplayed());
	}
	
	
	public void itemInfo(int itemNum) {
		String itemInfo= "//*[@id=\"item_"+itemNum+"_title_link\"]/div";
		System.out.println(itemInfo);
		chrome.findElement(By.xpath(itemInfo)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button")));
		chrome.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"inventory_item_container\"]/div/button")));
		chrome.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/button")).click();
		}
	
	
	
	public double getItemPrice(int itemNum) {
		if(itemNum==1) {
			getPriceItem1(itemNum);
		}
		else {
		String price= "(//*[@id=\"inventory_container\"]/div/div["+itemNum+"]/div[3]/div)";
	    itemprice= Double.parseDouble(chrome.findElement(By.xpath(price)).getText().replace("$",""));
		}
		return itemprice;
		}
	
	
	public double getPriceItem1(int itemNum) {
		//get price of item1
		String price1= "(//*[@id=\"inventory_container\"]/div/div["+itemNum+"]/div[3]/div)[2]";
		itemprice= Double.parseDouble(chrome.findElement(By.xpath(price1)).getText().replace("$",""));
		return itemprice;
	}
	
	
	public void shopCart() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shopping_cart_container\"]")));
		//click shopping cart button
		chrome.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
		
		//click checkout button
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")));
		chrome.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();
	}
	
	public void continueShop() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shopping_cart_container\"]")));
		//click shopping cart button
		chrome.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]")));
		chrome.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]")).click();
	}
	
	
	public void checkOut() {
		
		//fill out information
		chrome.findElement(By.id("first-name")).sendKeys("John");
		chrome.findElement(By.id("last-name")).sendKeys("Cena");
		chrome.findElement(By.id("postal-code")).sendKeys("389023");
		
		//click continue button
		chrome.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input")).click();
		
		//item total
		WebElement actualitemTotal = chrome.findElement(By.className("summary_subtotal_label"));
		wait.until(ExpectedConditions.visibilityOf(actualitemTotal));
		String[] actualitemTotalpr= actualitemTotal.getText().split("\\$");
		double itemTotal= Double.parseDouble(actualitemTotalpr[1]);
		System.out.println("itemTotal:"+itemTotal);
		
		//assertion for item total
		Assert.assertEquals(itemTotalPrice, itemTotal,0.0001);
		
		//Tax calculation
		double expTax= Math.round(itemTotal*0.08*100.0)/100.0; 
		
		//actual tax 
		WebElement actualTax= chrome.findElement(By.className("summary_tax_label"));
		String[] actualTaxpr= actualTax.getText().split("\\$");
		double tax= Double.parseDouble(actualTaxpr[1]);
		System.out.println("Tax:"+tax);
		
		//assertion for Tax
		Assert.assertEquals(expTax, tax, 0.0001);
		
		//Total bill
		double expTotal= itemTotal + tax;
		
		WebElement actualTotal= chrome.findElement(By.className("summary_total_label"));
		String[] actualTotalpr= actualTotal.getText().split("\\$");
		double total= Double.parseDouble(actualTotalpr[1]);
		System.out.println("Total:"+total);
		
		//assertion for total
		Assert.assertEquals(expTotal, total, 0.0001);
		
		//click finish button and check thank you for your order statement
		chrome.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();
		Assert.assertTrue(chrome.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).isDisplayed());
		}
		
	
	public void sideBar() throws InterruptedException {
		//click on side bar button 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-burger-button")));
		chrome.findElement(By.className("bm-burger-button")).click();
		
		//click on all items button 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_sidebar_link")));
		chrome.findElement(By.id("inventory_sidebar_link")).click();
		Assert.assertTrue(chrome.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/div")).isDisplayed());
		System.out.println(chrome.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/div")).isDisplayed());
		
		//click on side bar again
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-burger-button")));
		chrome.findElement(By.className("bm-burger-button")).click();
		
		//click on About button in side bar
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
		chrome.findElement(By.id("about_sidebar_link")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"entry-4G1LWMPtjUCWKvX2Y8oDN2\"]/div/div/div/div[1]/div/div/a")));
		Assert.assertEquals("Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs", chrome.getTitle());
		System.out.println(chrome.getTitle());
		
		//to get back to main page from about page
		chrome.navigate().back();
		Assert.assertTrue(chrome.findElement(By.className("bm-burger-button")).isDisplayed());
		
		//click on side bar again to do logout
		chrome.findElement(By.className("bm-burger-button")).click();
		
		
		//click on logout button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
		chrome.findElement(By.id("logout_sidebar_link")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
		Assert.assertTrue(chrome.findElement(By.id("user-name")).isDisplayed());
		}
	
	
	public void sortItems() throws InterruptedException {
	
		Select dropdown= new Select(chrome.findElement(By.className("product_sort_container")));
		dropdown.selectByValue("za");
		Thread.sleep(2000);
		Assert.assertEquals("Test.allTheThings() T-Shirt (Red)",chrome.findElement(By.xpath("//*[@class=\"inventory_item\"][1]/div[2]/a/div")).getText());
		dropdown.selectByValue("lohi");
		Thread.sleep(2000);
		Assert.assertEquals("$7.99", chrome.findElement(By.xpath("//*[@class=\"inventory_item\"][1]/div[3]/div[1]")).getText());
		dropdown.selectByValue("hilo");
		Thread.sleep(2000);
		Assert.assertEquals("$49.99",chrome.findElement(By.xpath("//*[@class=\"inventory_item\"][1]/div[3]/div[1]")).getText());
		dropdown.selectByValue("az");
		Thread.sleep(2000);
		Assert.assertEquals("Sauce Labs Backpack", chrome.findElement(By.xpath("//*[@class=\"inventory_item\"][1]/div[2]/a/div")).getText());
		}
	
}
