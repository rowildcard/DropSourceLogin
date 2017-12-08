package utilities;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DSsetup {
	
	//	SetUp() - basic setup for Chrome webdriver
	public static WebDriver SetUp() {
	
		//	setup Chrome to open incognito
		ChromeOptions options = new ChromeOptions();
		options.addArguments("-incognito");
		ChromeOptions capabilities = new ChromeOptions();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		//	Launch Chrome 
		WebDriver driver = new ChromeDriver(capabilities);
		
		//	turn on implicit wait timer
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  
		
		//	navigate to DropSource login page
		driver.get("https://app.dropsource.com/login");
		return driver;	
	}	// SetUp
	
}	//	class
