package com.org.projects.CucumberMobile.utils;

import com.org.projects.CucumberMobile.PageObjects.AppList;

import io.appium.java_client.android.AndroidDriver;

public class PageObjectManager {
	AndroidDriver driver;
	AppList applist;
	
	public PageObjectManager(AndroidDriver driver){
		this.driver=driver;
	}
	
	public AppList getAppListPage() {
		applist = new AppList(driver);
		return applist;
	}

}
