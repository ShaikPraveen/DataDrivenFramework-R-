package com.infofactors.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties 
{
	
	public static void main(String[] args) throws IOException 
	{
		//Reading Config Properties
		System.out.println(System.getProperty("user.dir"));
		Properties config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		System.out.println(config.getProperty("browser"));
		
		//Reading OR Properties
		Properties or=new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		or.load(fis);
		System.out.println(or.getProperty("bmlBtn"));
	}

}
