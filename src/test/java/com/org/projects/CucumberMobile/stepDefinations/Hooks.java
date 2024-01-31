package com.org.projects.CucumberMobile.stepDefinations;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.org.projects.CucumberMobile.utils.TestContextSetup;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;


public class Hooks {
TestContextSetup testContextSetup;
	
	public Hooks(TestContextSetup testContextSetup)
	{
		
		this.testContextSetup = testContextSetup;
	}
	@After
	public void AfterScenario() throws IOException, URISyntaxException
	{
		
		testContextSetup.testBase.DriverManager().quit();
		
	}
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException, URISyntaxException
	{
		WebDriver driver =testContextSetup.testBase.DriverManager();
		if(scenario.isFailed())
		{
		//screenshot
		File sourcePath= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
		}
		
	}
	
}
