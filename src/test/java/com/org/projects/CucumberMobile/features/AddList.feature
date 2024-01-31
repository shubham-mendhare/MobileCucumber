Feature: Add/Update/Delete List feature
  User addds the list, checks the added list, update the list and finally deletes the list
 

  Scenario Outline: Add List
  	Given user opens the List app
    And user taps on Add new list button
    Then user enters the list title as "NewList1"
    And user taps on save button
    Then user get display added list in list section

 
   Scenario Outline: Edit List
   	Given user opens the List app
    And user taps on Add new list button
    Then user enters the list title as "NewList1"
    And user taps on save button
    When user taps and hold on existing added list
    Then user selects Edit option
    And user updates the title to "NewUpdatedList1"
    And user taps on save button 
    Then user get display updated list in list section 

   @DeleteList 
   Scenario: Delete List
   	Given user opens the List app
    And user taps on Add new list button
    Then user enters the list title as "NewList1"
    And user taps on save button
    When user taps and hold on existing added list
    Then user selects Delete option
    And user presented with confirmation pop up
    Then user taps on Yes button 
    And user get display with message as "Grocery List Maker Start adding your grocery lists"
