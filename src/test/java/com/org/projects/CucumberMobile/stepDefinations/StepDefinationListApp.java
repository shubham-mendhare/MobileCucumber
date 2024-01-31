package com.org.projects.CucumberMobile.stepDefinations;


import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URISyntaxException;

import org.testng.Assert;

import com.org.projects.CucumberMobile.PageObjects.AppListTest;
import com.org.projects.CucumberMobile.utils.TestContextSetup;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationListApp {
	AndroidDriver driver;
	AppListTest applist;
	TestContextSetup testContextSetup;
	
	public StepDefinationListApp(TestContextSetup testContextSetup) throws MalformedURLException, URISyntaxException{
		this.testContextSetup = testContextSetup;
		applist = testContextSetup.pageObjectManager.getAppListPage();
		driver = testContextSetup.testBase.DriverManager();
	}

	@Given("user opens the List app")
	public void user_opens_the_list_app() throws URISyntaxException, IOException, InterruptedException {

//		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C\\:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				   // .withArgument(() -> "--use-plugins", "element-wait,device-farm,appium-dashboard,appium-reporter-plugin")
//				 	.withArgument(() -> "--use-plugins", "element-wait,device-farm,appium-dashboard")
//				    .withArgument(() -> "-ka", "800")
//				    .withArgument(() -> "--plugin-device-farm-platform", "android")
//				    .withTimeout(Duration.ofSeconds(120))
//					.withIPAddress("127.0.0.1")
//					.usingPort(4723)
//					.build();
	}

	@When("user taps and hold on existing added list")
	public void user_taps_and_hold_on_existing_title_list() {
		// Write code here that turns the phrase above into concrete actions
		// applist.longPressElement(applist.Addeditem);
		applist.actions.longPressElement(applist.Addeditem);
	}

	@Then("user selects Delete option")
	public void user_selects_delete_option() {
		// Write code here that turns the phrase above into concrete actions
		applist.DELETE.click();
	}

	@Then("user presented with confirmation pop up")
	public void user_presented_with_confirmation_pop_up() {
		// Write code here that turns the phrase above into concrete actions
		String actualText = applist.ConfirmationPopup.getAttribute("content-desc").toString();
		System.out.println(actualText);
		String expectedText = "Tap YES to delete the list.";
		Assert.assertEquals(actualText, expectedText);
	}

	@Then("user taps on Yes button")
	public void user_taps_on_yes_button() {
		// Write code here that turns the phrase above into concrete actions
		applist.YES.click();
	}

	@Then("user get display with message as {string}")
	public void user_get_display_with_message_as(String string) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		String actualText = applist.waterMarkWhenNoList.getAttribute("content-desc").toString();
		String expectedText = "Grocery List Maker\n"+ "Start adding your grocery lists";
		Assert.assertEquals(actualText, expectedText);
	}

	@Then("user selects Edit option")
	public void user_selects_edit_option() {
		// Write code here that turns the phrase above into concrete actions
		applist.EDIT.click();
	}

	@Then("user updates the title to {string}")
	public void user_updates_the_title_to_updated_title(String title) {
		// Write code here that turns the phrase above into concrete actions
		applist.InputTextField.click();
		driver.hideKeyboard();
		applist.InputTextField.sendKeys(title);
	}

	@Then("user taps on save button")
	public void user_taps_on_save_button() {
		// Write code here that turns the phrase above into concrete actions
		applist.SAVE_BTN.click();
	}

	@Then("user get display updated list in list section")
	public void user_get_display_updated_list_in_list_section() {
		// Write code here that turns the phrase above into concrete actions
		applist.Addeditem.isDisplayed();
	}

	@And("user taps on Add new list button")
	public void user_taps_on_add_new_list_button() {
		// Write code here that turns the phrase above into concrete actions
		applist.ADD_NEW_LIST_BTN.click();
	}

	@Then("user enters the list title as {string}")
	public void user_enters_the_list_title_as_title_test(String title) {
		// Write code here that turns the phrase above into concrete actions
		applist.InputTextField.click();
		driver.hideKeyboard();
		applist.InputTextField.sendKeys(title);
	}

	@Then("user get display added list in list section")
	public void user_get_display_added_list_in_list_section() {
		// Write code here that turns the phrase above into concrete actions
		applist.Addeditem.isDisplayed();
	}
}
