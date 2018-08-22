package com.infofactors.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.infofactors.utilities.MonitoringMail;
import com.infofactors.utilities.TestConfig;

public class TestHostAdd 
{
	
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		
		
		 MonitoringMail mail=new MonitoringMail();
	     String messageBody="http://"+InetAddress.getLocalHost().getHostAddress()+":8082/job/DataDrivenProject/Extent_20Reports/";
	     System.out.println(messageBody);
	     
	     mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	 	
	}
	
	 
	

}
