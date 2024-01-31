package com.org.projects.CucumberMobile.utils;

import com.org.projects.CucumberMobile.PageObjects.AppListTest;

import io.appium.java_client.android.AndroidDriver;

public class PageObjectManager {
	AndroidDriver driver;
	AppListTest applist;
	
	public PageObjectManager(AndroidDriver driver){
		this.driver=driver;
	}
	
	public AppListTest getAppListPage() {
		applist = new AppListTest(driver);
		return applist;
	}

}
