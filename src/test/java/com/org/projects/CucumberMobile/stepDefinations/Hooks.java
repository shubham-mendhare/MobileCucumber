package com.org.projects.CucumberMobile.stepDefinations;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.org.projects.CucumberMobile.utils.TestContextSetup;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;


public class Hooks {
TestContextSetup testContextSetup;
public static AppiumDriverLocalService service;
	
	public Hooks(TestContextSetup testContextSetup)
	{
		
		this.testContextSetup = testContextSetup;
	}
	
	@BeforeAll
	public static void setUp() {
	    if(service == null) {
	        service = new AppiumServiceBuilder()
	            .withAppiumJS(new File("C:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	            .withArgument(() -> "--use-plugins", "element-wait,device-farm,appium-dashboard")
	            .withArgument(() -> "-ka", "800")
	            .withArgument(() -> "--plugin-device-farm-platform", "android")
	            .withTimeout(Duration.ofSeconds(120))
	            .withIPAddress("127.0.0.1")
	            .usingPort(4723)
	            .build();
	        service.start();
	    }
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
	@AfterAll
    public static void tearDown() {
        // Stop Appium server
        if (service != null) {
            service.stop();
            service = null;
        }
    }
}
	

