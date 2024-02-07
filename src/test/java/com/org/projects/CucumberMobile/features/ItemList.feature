Feature: Add/Update/Delete item feature
  User addds the item, checks the added item, update the item and finally deletes the item from list
  
  Background:
		Given user open the app and adds "products" list
		Then user taps on created "product" list
    
    @AddItem
		Scenario: Add item
    Given user taps on Add new item button
    When user enters the item details as follows:
      | Title   | Quantity | Description           |
      | Mobile  | 10       | This is sample mobile |
    And user taps on save button
    Then user gets display added item in respective list


		
 		@EditItem 
   Scenario: Edit item
    Given user taps on Add new item button
    When user enters the item details as follows:
      | Title   | Quantity | Description           |
      | Mobile  | 10       | This is sample mobile |
    And user taps on save button
    Then user gets display added item in respective list
    And user taps on more option arrow
    Then user selects Edit option
    And user updates the Title, Quantity and Description in respective fields
      | Title   | Quantity | Description           |
      | Charger | 20       | This is sample mobile |
    And user taps on save button
    Then user get display updated item in respective list
		

   @DeleteItem
   Scenario: Delete item
    Given user taps on Add new item button
    When user enters the item details as follows:
      | Title   | Quantity | Description           |
      | Mobile  | 10       | This is sample mobile |
    And user taps on save button
    Then user gets display added item in respective list
    And user taps on more option arrow
    Then user selects Delete option
    And user presented with item delete confirmation pop up
    Then user taps on Yes button
    