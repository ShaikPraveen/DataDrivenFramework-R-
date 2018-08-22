package com.infofactors.testcaes;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.infofactors.base.TestBase;

public class BankManagerLoginTest extends TestBase
{
      @Test	
	  public void bankManagerLoginTest() throws InterruptedException, IOException 
	  {
    	  
    	  //System.setProperty("org.uncommons.reportng.escape-output","false");
    	  
    	    verifyEquals("abc", "xyz");
    	    Thread.sleep(3000);
    	    
    	    log.debug("=*==*=INSIDE LOGIN TEST=*==*=");
    	  
		    //driver.findElement(By.xpath(or.getProperty("bmlBtn"))).click();
    	  
    	    click("bmlBtn_XPATH");
		  
		 
		  //Thread.sleep(2);
		  
		  Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustTest_CSS"))), "Login Not Successfull");
		  
		  log.debug("=*==*=LOGIN SUCCESSFULLY EXCUTED=*==*=");// For logging 
		  
		  /*Reporter.log("Bank Manager Login Successfully");//For Reportng
		  Reporter.log("<a target=\"_blank\" href=\"D:\\Screenshot\\error.png\">Screenshot</a>");
		  Reporter.log("<br>");
		  Reporter.log("<a target=\"_blank\" href=\"D:\\Screenshot\\error.png\"><img src=\"D:\\Screenshot\\error.png\" height=200 width=200></img></a>");*/
		  
		 // Assert.fail("Bank Manger Login Fail"); // This is failing the Testcase purpose
		   

	  }

}
