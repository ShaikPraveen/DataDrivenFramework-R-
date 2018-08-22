package com.infofactors.testcaes;


import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.infofactors.base.TestBase;
import com.infofactors.utilities.TestUtil;

public class AddCustomerTest  extends TestBase
{
	
	
	//@Test(dataProvider="getData")
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException 
	{
		//Here i am using Hash Table concept instead of parameterization
		
		//String fname, String lname, String postCode, String alerttext, String runmode
		
		/*driver.findElement(By.cssSelector(or.getProperty("addCustTest"))).click();
		driver.findElement(By.cssSelector(or.getProperty("addCustFName"))).sendKeys(fname);
		driver.findElement(By.cssSelector(or.getProperty("addCustLName"))).sendKeys(lname);
		driver.findElement(By.cssSelector(or.getProperty("addCustPCode"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(or.getProperty("addCustBtn"))).click();*/
		
		
		if (!data.get("runmode").equals("Y"))
		{
			throw new SkipException("Skipping the testcase as the Run mode for data set is NO");
			
		}
		
		click("addCustTest_CSS");
		type("addCustFName_CSS",data.get("fname"));
		type("addCustLName_CSS",data.get("lname"));
		type("addCustPCode_CSS",data.get("postcode"));
		click("addCustBtn_CSS");
		
		Thread.sleep(2000); 
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		Thread.sleep(3000);
		alert.accept();
		Thread.sleep(3000);
		
		
		Reporter.log("Add Customer Successfully");
		
		//Assert.fail("Add Customer Fail"); // This is failing the Testcase purpose
		
	}
	
	 /*  @DataProvider
	   public Object[][] getData()
	   {
		   
		   String sheetName="AddCustomerTest";
		   int rows=excel.getRowCount(sheetName);
		   int cols=excel.getColumnCount(sheetName);
		   
		   Object[][] data=new Object[rows-1][cols];
		   
		   for(int rowNum=2; rowNum<=rows;rowNum++) 
		   {
			   
			   for(int colNum=0; colNum<cols; colNum++) 
			   {
				  //data[0][0]
				  data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
				    
			   }
			   
		   }
		   
		   return data;
		   
		   
	   }
*/
}
