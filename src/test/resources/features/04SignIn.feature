@signIn
Feature: SignIn page Verification
@TC001
Scenario: User gets error message when all fields are empty and clicks on SignIn button
	Given The user is in the Sign in page
	When User clicks "SignIn" button with all fields empty
	Then It should display "Please fill out this field." tooltip below Username textbox.
	@TC002
Scenario: User gets error message when enters only username and clicks on SignIn button
	Given The user is in the Sign in page
	When User enters only username and clicks on "SignIn" button
	|username|
	|NumpyNinja| 
	Then It should display "Please fill out this field." tooltip below Username textbox.
	@TC003
Scenario: User gets error message when enters only password and clicks on SignIn button
	Given The user is in the Sign in page
	When User enters only password and clicks on "SignIn" button
	|password|
	|Numpy@123| 
	Then It should display "Please fill out this field." tooltip below Username textbox.
	@TC004
Scenario Outline: User verifies invalid and valid combination of username and password and checks for expected message
	Given The user is in the Sign in page
	When User enters combination of username and password and clicks on "SignIn" button from given sheetname "<SheetName>" and rowNumber <RowNumber>
	
	Examples:
	|SheetName|RowNumber|
	|signin|0|
	|signin|1|	
	|signin|2|
	|signin|3|
	