package com.dropdown.testtask;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class Dropdowntest {

	WebDriver driver;
	
	@BeforeTest
	public void launchingchrome() {
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("window-size=1400,800");
		  options.addArguments("headless");
		  System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
			}
	
	  @Test(priority = 0)
	  public void navigatingtourl() throws InterruptedException {
		  driver.get("http://tve-admin.engnew-spectrum.net/tve-customer-care/AdminLogin.jsp");
		 assertTrue(driver.getTitle().contains("Admin Login"));
		 
	  }

  @Test(priority = 1)
  public void checkloginfunctionality() {
	  ExplicitWaitmethod(driver,  driver.findElement(By.id("userid")), 30);
		 driver.findElement(By.id("userid")).sendKeys("admin");
		 ExplicitWaitmethod(driver, driver.findElement(By.id("password")), 30); 
		 driver.findElement(By.id("password")).sendKeys("admin");
 ExplicitWaitmethod(driver,  driver.findElement(By.xpath("//*[@id=\"adminloginForm\"]/input")), 30);
	 driver.findElement(By.xpath("//*[@id=\"adminloginForm\"]/input")).submit();
	 assertTrue(driver.getTitle().contains("Home Page"));		  
  }
  @Test(priority = 2)
  public void navigatetocheckprofilestatus() {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,1000)");
	  ExplicitWaitmethod(driver, driver.findElement(By.linkText("Check Profiles")), 120); 
	  Actions action = new Actions(driver);
	  action.moveToElement(driver.findElement(By.linkText("Check Profiles"))).build().perform();
	  driver.findElement(By.linkText("Check Profiles")).click();
	  System.out.println(driver.getTitle());
	  assertTrue(driver.getTitle().contains("Check Policy"));
  }
  @Test(priority = 3)
  public void handledropdown() throws IOException {

	  
	  
	  for (int i =1; i<=159; i++) {
		  driver.navigate().refresh();
		  ExplicitWaitmethod(driver , driver.findElement(By.id("policies")), 180);
	  
		  WebElement mySelectElm = driver.findElement(By.id("policies")); 
	
		  Select mySelect= new Select(mySelectElm);
		  mySelect.selectByIndex(i);
		  String sValue = mySelect.getOptions().get(i).getText();
		  System.out.println(sValue);
		  ExplicitWaitmethod(driver , driver.findElement(By.xpath("//*[@id=\"checkPolicies\"]/input")), 300); 
		  driver.findElement(By.xpath("//*[@id=\"checkPolicies\"]/input")).submit();
		  ExplicitWaitmethodfordropdown(driver, driver.findElement(By.xpath("//*[@id=\"checkPolicies\"]/input")), 60);
		  
		}
	
		}
	 
	
  void ExplicitWaitmethod(WebDriver drvier, WebElement element, int timeout) {
	 new  WebDriverWait(driver,  timeout).until(ExpectedConditions.visibilityOf(element));
	 }
  
  void ExplicitWaitmethodfordropdown(WebDriver drvier, WebElement locator, int timeout) {
	 new  WebDriverWait(driver,  timeout).until(ExpectedConditions.elementToBeClickable(locator));
	 }
  
}
