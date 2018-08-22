package com.infofactors.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.infofactors.utilities.ExcelReader;
import com.infofactors.utilities.ExtentManager;
import com.infofactors.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase
{
	 /*Webdriver 
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, Extent Reports
	 */
	
	
	  public static WebDriver driver;
	  public static Properties config=new Properties();
	  public static Properties or=new Properties();
	  public static FileInputStream fis;
	  public static Logger log=Logger.getLogger("devpinoyLogger");
	  public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\Testdata.xlsx");
	  public static WebDriverWait wait;
	  public ExtentReports rep=ExtentManager.getInstance();
	  public static ExtentTest test;
	  public static String browser;
	  
	  
	
	  @BeforeSuite
	  public void setUp() 
	  {
		  
		  if (driver==null)
		  {
			  
			   try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  try {
				config.load(fis);
				log.debug("=*==*=CONFIG FILE LOADED=*==*=");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  try {
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  try {
				or.load(fis);
				log.debug("=*==*=OR FILE LOADED=*==*=");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  //For Jenkins Purpose
			  
			  if (System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) 
			  {
				  
				browser=System.getenv("browser");
			   }else 
			   {
				browser=config.getProperty("browser");
			     }
			     config.setProperty("browser", browser);
			  
			  
			  if (config.getProperty("browser").equals("firefox"))
			  {
				  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
				  driver=new FirefoxDriver();
				  log.debug("=*==*=FIREFOX BROWSER LAUNCHED=*==*=");
			}
			  else if (config.getProperty("browser").equals("chrome"))
			  {
				  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				  driver=new ChromeDriver();
				  log.debug("=*==*=CHROME BROWSER LAUNCHED=*==*=");
			}
			  else if (config.getProperty("browser").equals("ie"))
			  {
				  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				  driver=new InternetExplorerDriver();	  
			}
			  
			  driver.get(config.getProperty("testsiteurl"));
			  log.debug("NAVIGATE TO "+ config.getProperty("testsiteurl"));
			  driver.manage().window().maximize();
			  log.debug("=*==*=BROWSER MAXIMIZED=*==*=");
			  driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			  wait=new WebDriverWait(driver, 5);
		}  
	  }
	  
	  
	  
	    public void click(String locator) 
	    {
	    	if (locator.endsWith("_CSS")) 
	    	{
	    		driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			}
	    	else if (locator.endsWith("_XPATH")) 
			{
				driver.findElement(By.xpath(or.getProperty(locator))).click();
			}
				
			else if (locator.endsWith("_ID")) 
			{
				driver.findElement(By.id(or.getProperty(locator))).click();
			}	
			
	    	test.log(LogStatus.INFO, "Clicking On : "+ locator);
	    }
	  
	    
	    public void type(String locator, String value) 
	    {
	    	if (locator.endsWith("_CSS")) 
	    	{
	    	
	    	driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
	    	}
	    	else if (locator.endsWith("_XPATH")) 
	    	{
	    	
	    	driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
	    	}
	    	else if (locator.endsWith("_ID")) 
	    	{
	    	
	    	driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
	    	}
	    	test.log(LogStatus.INFO, "Typing in  : "+locator + "   Entered value as : "+value);
	    	
	    }
	 
	     public boolean isElementPresent(By by) 
	     {
	    	 
	    	 try 
	    	 {
	    		 driver.findElement(by);
	    		 return true;
	    		 
	    	 }catch(NoSuchElementException e) 
	    	 {
	    		 return false;
	    	 }
	     }
	     
	     
	          public static  void verifyEquals(String expected, String actual) throws IOException 
	          {
	        	  
	        	   try 
	                {
	        		   Assert.assertEquals(actual, expected);
	        		   
	        	   }catch(Throwable t)
	        	   {
	        		   TestUtil.captureScreenshot();
	        		   //ReportNG
	        		   Reporter.log("<br>"+"Verification Failure : "+t.getMessage()+"<br>");
	        		   Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
	        		   Reporter.log("<br>");
	        		   Reporter.log("<br>");
	        		   
	        		   //Extent Report
	        		   
	        		test.log(LogStatus.FAIL, "Verification Failed with Exception : "+t.getMessage());
	       			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
	     
	        	   }  
	          }
	          
	          
	          static WebElement dropdown;
	          public void select(String locator, String value)
	          {
	        	  
	        	  if (locator.endsWith("_CSS")) 
	  	    	{
	  	    	
	        		  dropdown=driver.findElement(By.cssSelector(or.getProperty(locator)));
	  	    	}
	  	    	else if (locator.endsWith("_XPATH")) 
	  	    	{
	  	    	
	  	    		dropdown=driver.findElement(By.xpath(or.getProperty(locator)));
	  	    	}
	  	    	else if (locator.endsWith("_ID")) 
	  	    	{
	  	    	
	  	    		dropdown=driver.findElement(By.id(or.getProperty(locator)));
	  	    	}
	        	  
	        	  Select sele=new Select(dropdown);
	        	  sele.selectByVisibleText(value);
	  	    	test.log(LogStatus.INFO, "Selecting From Dropdown  : "+locator + "  Value as : "+value);
	        	  
	        	  
	        	  
	          }
	     
	     
	     
	   
	  @AfterSuite
	  public void teardown() 
	  {
		  if (driver!=null)
		  {
		  driver.quit();	
		}
		  
		  log.debug("=*==*=TEST EXECUTATION COMPLETED=*==*=");
		  
	  }
}
