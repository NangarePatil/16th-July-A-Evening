package neoStoxTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import neoStoxBase.BaseNew;
import neoStoxPOM.NeoStoxHomePage;
import neoStoxPOM.NeoStoxLoginPage;
import neoStoxPOM.NeoStoxPasswordPage;
import neoStoxPOM.NeoStoxSignInPage;
import neoStoxPOM.UtilityNew;

@Listeners(neoStoxBase.Listener.class)
public class ValidateNeoStoxAccBalance2 extends BaseNew
{
	NeoStoxLoginPage login;
	NeoStoxPasswordPage password;
	NeoStoxHomePage home;
	NeoStoxSignInPage signIn;

	@BeforeClass
	public void launchNeoStox() throws InterruptedException, EncryptedDocumentException, IOException
	{
	launchBrowser();//form base class
	login= new NeoStoxLoginPage(driver);
	password= new NeoStoxPasswordPage(driver);
	home= new NeoStoxHomePage(driver);
	signIn= new NeoStoxSignInPage(driver);
	}
	
	@BeforeMethod
	public void logintoNeoStox() throws EncryptedDocumentException, IOException, 
	InterruptedException
	{
	signIn.clickOnSignInButton(driver);
	Thread.sleep(1000);
	login.sendMobileNum(driver, UtilityNew.readDataFrompropertyFile("MobileNum"));
	login.clickOnSignInButton(driver);
	UtilityNew.wait(driver, 1000);
	password.enterPassword(driver, UtilityNew.readDataFrompropertyFile("Passcode"));
	Thread.sleep(1000);
	password.clickOnSubmitButton(driver);
	Thread.sleep(5000);
	home.handlePopUp(driver);
	}
  @Test
  public void validateAccBalance() throws EncryptedDocumentException, IOException
  {
  Assert.assertNotNull(home.getAccBalance(driver),"TC failed Unable to fetch account Balance");
  UtilityNew.screenshot(driver, "ACCBalance");
  }
  @Test (priority = -1)
   public void validateUserID() throws EncryptedDocumentException, IOException
  {
	  
  Assert.assertEquals(home.getActualUserName(driver),UtilityNew.readDataFrompropertyFile("UserName"),"TC is failed Actual and expected User Name are not matching");
  UtilityNew.screenshot(driver, home.getActualUserName(driver));
  }

  
  @AfterMethod
  public void logOutFromNeoStox() throws InterruptedException
  {
	  Thread.sleep(5000);
  home.logOut(driver);
  }
  
  
  @AfterClass
  public void closeBrowser()
  {
  //Reporter.log("closing browser", true);
  driver.close();
  }
}
