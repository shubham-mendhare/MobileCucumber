package com.org.projects.CucumberMobile.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.org.projects.CucumberMobile.utils.AndroidAction;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ItemListTest extends AndroidAction {
	AndroidDriver driver;
	
	public ItemListTest(AndroidDriver driver) {
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(accessibility ="ADD NEW ITEM")
	public WebElement ADD_NEW_ITEM_BTN;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Add Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[1]")
	public WebElement INPUT_TITLE;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Add Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[2]")
	public WebElement INPUT_QUANTITY;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Add Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[3]")
	public WebElement INPUT_DISCRIPTION;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Edit Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[1]")
	public WebElement UPDATE_TITLE;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Edit Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[2]")
	public WebElement UPDATE_QUANTITY;
	
	@AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Edit Item\"]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText[3]")
	public WebElement UPDATE_DISCRIPTION;
	
	
	@AndroidFindBy(className ="android.widget.ImageView")
	public WebElement AddedItem;
	
	@AndroidFindBy(accessibility = "Tap YES to delete the item.")
	public WebElement ConfirmationPopup;
}
