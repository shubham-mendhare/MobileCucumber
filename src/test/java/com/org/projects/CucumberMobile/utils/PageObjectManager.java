package com.org.projects.CucumberMobile.utils;

import com.org.projects.CucumberMobile.PageObjects.AppListTest;
import com.org.projects.CucumberMobile.PageObjects.ItemListTest;

import io.appium.java_client.android.AndroidDriver;

public class PageObjectManager {
	AndroidDriver driver;
	AppListTest applist;
	ItemListTest itemlist;
	
	public PageObjectManager(AndroidDriver driver){
		this.driver=driver;
	}
	
	public AppListTest getAppListPage() {
		applist = new AppListTest(driver);
		return applist;
	}
	
	public ItemListTest getItemListPage() {
		itemlist = new ItemListTest(driver);
		return itemlist;
	}

}
