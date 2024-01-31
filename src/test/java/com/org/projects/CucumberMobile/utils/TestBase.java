package com.org.projects.CucumberMobile.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class TestBase {
	AndroidDriver driver;
	
	public AndroidDriver DriverManager() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("emulator-5554");
		options.setApp(System.getProperty("user.dir") + "/app-release.apk");
		if (driver == null) {
			driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		}
		return driver;
	}
}


