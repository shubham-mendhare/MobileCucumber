package com.org.projects.CucumberMobile.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.appium.java_client.android.AndroidDriver;



public class TestContextSetup {
	public AndroidDriver driver;
	public TestBase testBase;
	public PageObjectManager pageObjectManager;
	
	
	public TestContextSetup() throws MalformedURLException, URISyntaxException{
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.DriverManager());
	}
}
