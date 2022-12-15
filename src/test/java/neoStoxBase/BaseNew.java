package neoStoxBase;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

import neoStoxPOM.UtilityNew;

public class BaseNew 

{

	protected static WebDriver driver;
	
	public void launchBrowser() throws InterruptedException, EncryptedDocumentException, IOException
	{
        System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\selenium-java-4.4.0 (1)//chromedriver.exe");
		
	    driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(UtilityNew.readDataFrompropertyFile("url"));
		
		Reporter.log("Launching Browser",true);
		
		neoStoxPOM.UtilityNew.wait(driver, 1000);
		
			
	}
	public static void closingBrowser(WebDriver driver) throws InterruptedException
	{
	Reporter.log("closing browser", true);
	Thread.sleep(500);
	driver.close();
	}
	
}
