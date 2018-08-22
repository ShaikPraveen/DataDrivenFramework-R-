package com.infofactors.testcaes;


import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.infofactors.base.TestBase;
import com.infofactors.utilities.TestUtil;

public class OpenAccountTest  extends TestBase
{
	
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException 
	{
		
		//String customer, String currency
		
		//Open Account Code
		
		click("openAccount_CSS");
		select("customer_CSS",data.get("Customer"));
		select("currency_CSS",data.get("Currency"));
		click("process_CSS");
		
		Thread.sleep(2000);
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
		
	}
	
	   
}
