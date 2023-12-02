@home
Feature: HOME

Background:
	@TC001
  Scenario: Data Structures DropDown Verification
    Given User opens home page
    When User clicks Data Structures dropdown
    Then User should see 6 different data structure entries in the dropdown
  @TC002 
 Scenario: Verify User not logged in message when user clicks on any data structure entry
 		Given User opens home page
 		When The user clicks any of the Get Started buttons below the data structures   
 		Then It should alert the user with a message "You are not logged in"
 	@TC003	
 Scenario: Verify User not logged in message when user selects any data structure from the dropdown with signIn
 		Given User opens home page
 		When The user clicks any of the data structures item from the dropDown list   
 		Then It should alert the user with a message "You are not logged in"
 @TC004
  Scenario: Verify if user is redirected to sign in page	if clicked on "Sign In"	
 	  Given User opens home page
 		When User clicks on SignIn
 		Then The user should be redirected to Sign in page	
 		@TC005
  Scenario: Verify if user is redirected to register page	if clicked on "Register"	
 		Given User opens home page
 		When User clicks on Register
 		Then The user should be redirected to Register page			