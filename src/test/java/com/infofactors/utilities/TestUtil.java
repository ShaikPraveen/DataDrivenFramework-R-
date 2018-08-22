package com.infofactors.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.infofactors.base.TestBase;

public class TestUtil  extends TestBase
{
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException
	{
		
		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//screenshotName="error.png";
		Date date= new Date();
		screenshotName=date.toString().replace(":", "_").replace(" ", "_")+".png";
				
		
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
		
		
	}
	
	   @DataProvider(name="dp")
	   public Object[][] getData(Method m)
	   {
		   
		   String sheetName=m.getName();
		   int rows=excel.getRowCount(sheetName);
		   int cols=excel.getColumnCount(sheetName);
		   
		  
		   Object[][] data=new Object[rows-1][1];//[cols]
		   
		   Hashtable<String, String> table=null;
		   
		   for(int rowNum=2; rowNum<=rows;rowNum++) 
		   {
			   table=new Hashtable<String, String>();
			   
			   for(int colNum=0; colNum<cols; colNum++) 
			   {
				  //data[0][0]
				   table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				   //data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
				   data[rowNum-2][0]=table;				    
			   }
			   
		   }
		   
		   return data;
		   
		   
	   }

	
	public static boolean isTestRunnable(String testName, ExcelReader excel) 
	{
		String sheetName="test_suite";
		int rows=excel.getRowCount(sheetName);
		
		for (int rowNum=2; rowNum<=rows; rowNum++) 
		{
			
			String testCase=excel.getCellData(sheetName, "TCID", rowNum);
			
			if (testCase.equalsIgnoreCase(testName)) 
			{
				String runmode=excel.getCellData(sheetName, "Runmode", rowNum);
				
				if (runmode.equalsIgnoreCase("Y"))
				
					return true;
					
					else
						
						return false;
					
				}	
			}
			return false;
		
		}
		
		
		
	}
	
	

