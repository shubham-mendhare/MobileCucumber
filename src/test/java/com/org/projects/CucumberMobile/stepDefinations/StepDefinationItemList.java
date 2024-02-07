package com.org.projects.CucumberMobile.stepDefinations;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.org.projects.CucumberMobile.PageObjects.AppListTest;
import com.org.projects.CucumberMobile.PageObjects.ItemListTest;
import com.org.projects.CucumberMobile.utils.TestContextSetup;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationItemList {
	AndroidDriver driver;
	AppListTest applist;
	ItemListTest itemlist;
	TestContextSetup testContextSetup;
	
	public StepDefinationItemList(TestContextSetup testContextSetup) throws MalformedURLException, URISyntaxException{
		this.testContextSetup = testContextSetup;
		applist = testContextSetup.pageObjectManager.getAppListPage();
		itemlist = testContextSetup.pageObjectManager.getItemListPage();
		driver = testContextSetup.testBase.DriverManager();
	}
	
	@Given("user open the app and adds {string} list")
	public void user_open_the_app_and_adds_product_list(String product) {
		applist.AddList(product);
	}
	
	@Given("user taps on created {string} list")
	public void user_taps_on_created_product_list(String product) {
		applist.Addeditem.click();
	}
	
	@Given("user taps on Add new item button")
	public void user_taps_on_Add_new_item_button() {
		itemlist.ADD_NEW_ITEM_BTN.click();
	}
	
	@When("user enters the item details as follows:")
	public void user_enters_the_item_details_in_Title_Quantity_and_Discription_fields(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> data : dataList) {
            String title = data.get("Title");
            String quantity = data.get("Quantity");
            String description = data.get("Description");
            
            itemlist.INPUT_TITLE.click();
            itemlist.INPUT_TITLE.sendKeys(title);
            
            itemlist.INPUT_QUANTITY.click();
            itemlist.INPUT_QUANTITY.sendKeys(quantity);
            
            itemlist.INPUT_DISCRIPTION.click();
            itemlist.INPUT_DISCRIPTION.sendKeys(description);
        }
        	
	}
	
    @Then("user gets display added item in respective list")
    public void user_gets_display_added_item_in_respective_list() {
    	itemlist.AddedItem.isDisplayed();
    }
    
    @And("user taps on more option arrow")
    public void user_taps_on_more_option_arrow() {
    	itemlist.TapByCoordinates(1001, 363);
    }
    
    @And("user updates the Title, Quantity and Description in respective fields")
    public void user_updates_title_quantity_and_description(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> data : dataList) {
            String title = data.get("Title");
            String quantity = data.get("Quantity");
            String description = data.get("Description");

            // Implement code to update the Title, Quantity, and Description in respective fields
            // Example:
            // Assume you have methods to interact with your UI elements like setText(), clickButton(), etc.
            itemlist.UPDATE_TITLE.click();
            itemlist.UPDATE_TITLE.clear();
            itemlist.UPDATE_TITLE.sendKeys(title);
            
            itemlist.UPDATE_QUANTITY.click();
            itemlist.UPDATE_QUANTITY.clear();
            itemlist.UPDATE_QUANTITY.sendKeys(quantity);
            
            itemlist.UPDATE_DISCRIPTION.click();
            itemlist.UPDATE_DISCRIPTION.clear();
            itemlist.UPDATE_DISCRIPTION.sendKeys(description);
        }
    }

    @Then("user get display updated item in respective list")
    public void user_gets_display_updated_item() {
        // Implement code to verify that the updated item is displayed in the respective list
    	itemlist.AddedItem.isDisplayed();
    }
    
    @Then("user presented with item delete confirmation pop up")
    public void user_presented_with_item_delete_confirmation_pop_up() {
        // Implement code to verify that the updated item is displayed in the respective list
    	String actualText = itemlist.ConfirmationPopup.getAttribute("content-desc").toString();
		System.out.println(actualText);
		String expectedText = "Tap YES to delete the item.";
		Assert.assertEquals(actualText, expectedText);
    }
    
}
