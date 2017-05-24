package com.testseleniumtask;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestCase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver","F:\\Soft\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","F:\\Soft\\chromedriver\\chromedriver.exe");
		//driver = new ChromeDriver();
		baseUrl = "http://igorakintev.ru";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCaseJobOffer() throws Exception {
		driver.get(baseUrl + "/admin/");
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username")).sendKeys("silenium");
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password")).sendKeys("super_password");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		try {
			assertEquals(driver.findElement(By.cssSelector("h1.dashboard-title")).getText(), "Панель управления");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector("a.addlink > span.icon")).click();
		try {
			assertEquals(driver.findElement(By.cssSelector("#content > h1")).getText(), "Добавить entry");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("id_title")).clear();
		driver.findElement(By.id("id_title")).sendKeys("Title43565463456");
		driver.findElement(By.id("id_slug")).clear();
		driver.findElement(By.id("id_slug")).sendKeys("Slug43565463456");
		driver.findElement(By.id("id_text_markdown")).clear();
		driver.findElement(By.id("id_text_markdown")).sendKeys("Slug43565463456");
		driver.findElement(By.id("id_text")).clear();
		driver.findElement(By.id("id_text")).sendKeys("Slug43565463456");
		//driver.findElement(By.cssSelector("input[class=\"default\"]")).click();
		driver.findElement(By.className("default")).click();
		driver.get(baseUrl + "/blog/");
		driver.findElement(By.linkText("Title43565463456")).click();
		driver.get(baseUrl + "/admin/blog/entry");
		driver.findElement(By.linkText("Title43565463456")).click();
		driver.findElement(By.linkText("Удалить")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.linkText("Выйти")).click();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if ("".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
