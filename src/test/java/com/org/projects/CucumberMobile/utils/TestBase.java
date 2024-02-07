package com.org.projects.CucumberMobile.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.org.projects.CucumberMobile.stepDefinations.Hooks;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class TestBase {
	AndroidDriver driver;

	public AndroidDriver DriverManager() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(Hooks.prop.getProperty("AndroidDeviceName"));
		options.setApp(System.getProperty("user.dir") + Hooks.prop.getProperty("APK_PATH"));
		if(Hooks.prop.getProperty("ContinueDriverSession").equalsIgnoreCase("true")) {
		options.setCapability("noReset", true);
		options.setCapability("fullReset", false);
		}
		if (driver == null) {
			driver = new AndroidDriver (new URI("https://hub.browserstack.com/wd/hub").toURL(),options);
		//	driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
		//	driver = new AndroidDriver(Hooks.service.getUrl(), options);
		}
		return driver;
	}
}
