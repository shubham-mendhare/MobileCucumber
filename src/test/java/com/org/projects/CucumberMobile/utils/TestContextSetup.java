package com.org.projects.CucumberMobile.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Properties;

import io.appium.java_client.android.AndroidDriver;



public class TestContextSetup {
	public AndroidDriver driver;
	public TestBase testBase;
	public PageObjectManager pageObjectManager;;
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	public TestContextSetup() throws MalformedURLException, URISyntaxException{
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.DriverManager());
	}
	
	   
}
