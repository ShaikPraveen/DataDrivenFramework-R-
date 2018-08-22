package com.infofactors.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.infofactors.base.TestBase;
import com.infofactors.utilities.MonitoringMail;
import com.infofactors.utilities.TestConfig;
import com.infofactors.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase  implements ITestListener, ISuiteListener
{
	
	public  String messageBody;
	
	// Listeners Concept
	
	
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

      public void onStart(ITestContext arg0) {
		
		
	}
      
      public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  		// TODO Auto-generated method stub
  		
  	}

	
	public void onTestFailure(ITestResult arg0) {
		
		  System.setProperty("org.uncommons.reportng.escape-output","false");
		  try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		    test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with Exception : "+arg0.getThrowable());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		      
		  
		  
		  //Reporter.log("Capturing Screenshot");
		  Reporter.log("Click To See Screen Shot");
		  //Reporter.log("<a target=\"_blank\" href=\"D:\\Screenshot\\error.png\">Screenshot</a>");
		  Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		  Reporter.log("<br>");
		  Reporter.log("<br>"); 
		 // Reporter.log("<a target=\"_blank\" href=\"D:\\Screenshot\\error.png\"><img src=\"D:\\Screenshot\\error.png\" height=200 width=200></img></a>");
		  Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		 rep.endTest(test);
		 rep.flush();
	
	}
	
	
	public void onTestSkipped(ITestResult arg0) {
		
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() +" Skipped the test as Run mode as NO");
		rep.endTest(test);
		rep.flush();
		
	}
	
       public void onTestStart(ITestResult arg0) {
		
		
		test=rep.startTest(arg0.getName().toUpperCase());
		//Runmode  - Y
		
		if (!TestUtil.isTestRunnable(arg0.getName(), excel)) 
		{
			throw new SkipException("Skipping the test " +arg0.getName().toUpperCase() +" as tye Run mode NO");
		
		}
		
	   }
       
       
       public void onTestSuccess(ITestResult arg0) {
   		
   		
   		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+" PASS");
   		rep.endTest(test);
   		rep.flush();
   		
   	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite arg0) {
		
		 MonitoringMail mail=new MonitoringMail();
	     //String messageBody;
		try {
			messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8082/job/DataDrivenProject/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
       
      
	

}
