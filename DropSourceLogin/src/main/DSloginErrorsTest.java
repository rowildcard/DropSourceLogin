package main;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utilities.DSsetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//	DropSource Assessment 12-05-2017
//
//	For the DropSource login page, verify login error messages.
//		- email left blank
//		- invalid email address format
//		- email doesn't exist
//		- password left blank
//		- invalid password format
//		- password does match email address

public class DSloginErrorsTest {
	
	static WebDriver driver;
	static SoftAssert sAssert = new SoftAssert();
	
	//	Valid input field values 
	static String sValidEmail = "cherbert@dropsource.com";
    static String sValidPsswd = "abCD56gh";

    //	Invalid input field values:   BLANK, FORMAT, UNKNOWN
	static String[] sInvalidEmail = new String[] { "", "rhonda@", "noaccount@email.com" };
    static String[] sInvalidPsswd = new String[] { "", "123", "abCD56gh" };
	
	//	Expected error message types: BLANK, FORMAT, UNKNOWN
	static String[] sEmailErrMsgs = new String[] { "You left the email field blank",
			                                       "This is not a valid email",
			                                       "This email doesn't exist in our records" };
	static String[] sPsswdErrMsgs = new String[] { "You left the password field blank.",
								                   "Passwords must be 8-64 characters and contain at least one uppercase letter, one lowercase letter, one number, and cannot contain any starting or ending spaces",
								                   "This password doesn't match the above email" };
	
	//	Before each @Test --------------------------
	@BeforeMethod
	public static void BeforeTests() {
		driver = DSsetup.SetUp();
	}
	
	//	After each @Test ---------------------------	
	@AfterMethod
	public static void AfterTests() {
		driver.quit();
	}
	
	//	@Test --------------------------------------	
	@Test
	public static void LoginErrorsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//	Page Objects:
		//		- Email input field
		By byEmailInput = By.xpath(".//*[@id='root']/div/div[2]/form/div[2]/div[2]/span/input");
		//		- Password input field
		By byPsswdInput = By.xpath(".//*[@id=\'root\']/div/div[2]/form/div[3]/div[2]/span/input");
		//		- Email error field
		By byEmailError = By.xpath(".//*[@id=\"root\"]/div/div[2]/form/div[2]/div[2]/span/div/div[1]");
		//		- Password error field
		By byPsswdError = By.xpath(".//*[@id=\"root\"]/div/div[2]/form/div[3]/div[2]/span/div/div[1]");
		//		- Login button
		By byLoginBtton = By.xpath(".//*[@id=\"root\"]/div/div[2]/form/div[4]/button/div");
		
		//	Verify Error Messages...
		//
		//	... for Email 
		driver.findElement(byPsswdInput).clear();
		driver.findElement(byPsswdInput).sendKeys(sValidPsswd);
		//	... for each invalid email type
		for (int i = 0; i < sEmailErrMsgs.length; i++) {
			driver.findElement(byEmailInput).clear();
			driver.findElement(byEmailInput).sendKeys(sInvalidEmail[i]);
			driver.findElement(byLoginBtton).click();
			
			wait = new WebDriverWait(driver, 6);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byEmailError) );
			//	... verify the correct error message is displayed.
			sAssert.assertEquals(driver.findElement(byEmailError).getText(), sEmailErrMsgs[i]);
			
		};	//	Email
		
		driver.navigate().refresh();	//	clear the slate
		
		//	... for Password
		driver.findElement(byEmailInput).clear();
		driver.findElement(byEmailInput).sendKeys(sValidEmail);
		//	... for each invalid password type
		for (int i = 0; i < sPsswdErrMsgs.length; i++) {
			driver.findElement(byPsswdInput).clear();
			driver.findElement(byPsswdInput).sendKeys(sInvalidPsswd[i]);
			driver.findElement(byLoginBtton).click();
			
			wait = new WebDriverWait(driver, 6);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byPsswdError) );
			//	... verify the correct error message is displayed.
			sAssert.assertEquals(driver.findElement(byPsswdError).getText(), sPsswdErrMsgs[i]);
			
		};	//	Password
		
		sAssert.assertAll();
		
	}	//	LoginErrorsTest()
}	//	class