package com.org.projects.CucumberMobile.PageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.org.projects.CucumberMobile.utils.AndroidAction;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppListTest extends AndroidAction {
	
	AndroidDriver driver;
//	public AndroidAction actions;
	
	
	@AndroidFindBy(accessibility ="ADD NEW LIST")
	public WebElement ADD_NEW_LIST_BTN;
	
	@AndroidFindBy(className = "android.widget.EditText")
	public WebElement InputTextField;
	 
	@AndroidFindBy(accessibility = "SAVE")
	public WebElement SAVE_BTN;
	
	@AndroidFindBy(className = "android.widget.ImageView")
	public WebElement Addeditem;
	
	@AndroidFindBy(accessibility = "Edit")
	public WebElement EDIT;
	
	@AndroidFindBy(accessibility = "Delete")
	public WebElement DELETE;
	
	@AndroidFindBy(accessibility = "YES")
	public WebElement YES;
	
	@AndroidFindBy(accessibility = "Tap YES to delete the list.")
	public WebElement ConfirmationPopup;
	
	
	@AndroidFindBy(className = "android.widget.ImageView")
	public WebElement waterMarkWhenNoList;
	

	
	
	public AppListTest(AndroidDriver driver){
		super(driver);
	    this.driver = driver;
	 //   actions=new AndroidAction(driver);
	    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	    
	}
	
	public void AddList(String listdetails) {
		ADD_NEW_LIST_BTN.click();
		InputTextField.click();
		driver.hideKeyboard();
		InputTextField.sendKeys(listdetails);
		SAVE_BTN.click();
		
	}
	
	public void EditList(String Updatedlistdetails) {
	//	actions.longPressAction(Addeditem);
		longPressElement(Addeditem);
		EDIT.click();
		InputTextField.click();
		driver.hideKeyboard();
		InputTextField.sendKeys(Updatedlistdetails);
		SAVE_BTN.click();
	}
	
	public void DeleteList() {
	//	actions.longPressAction(Addeditem);
		longPressElement(Addeditem);
		DELETE.click();
		YES.click();
	}
	
	

}
