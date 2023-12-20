@register

Feature: Verification of Register Page
@TC001
Scenario: User gets error message when all fields are empty
	Given User opens Register Page
	When User clicks Register button with all fields empty
	Then User gets error message  "Please fill out this field." tooltip below Username textbox.
@TC002
Scenario: User gets error message when password fields are empty
	Given User opens Register Page
	When User enters username and clicks on Register button 
	|username|
	|NinjaCode|
	Then User gets error message  "Please fill out this field." tooltip below Password textbox.
@TC003
Scenario: User gets error message when confirm password field is empty
	Given User opens Register Page
	When User enters username and password and clicks on Register button 
	|username|password|
	|NinjaCode|Tester12345|
	Then User gets error message  "Please fill out this field." tooltip below Confirm Password textbox.
@TC004	 
Scenario: User gets error message when invalid characters are entered in username
	Given User opens Register Page
	When User enters characters other than letters, digits and few special characters
	
	|username|password|confirmPassword|
	|test!$0<>|Tester1234|Tester1234|
	Then User gets error message "password_mismatch:The two password fields didn’t match."
@TC005	
Scenario: User gets error message when entered different passwords
	Given User opens Register Page
	When User enters different passwords in Password and Password Confirmation field
	
	|username|password|confirmPassword|
	|NumNin@gmail.com|Tester1234|Tester1245|
	
	Then 	User gets error message "password_mismatch:The two password fields didn’t match."
@TC006	
Scenario: User gets error message when entered password with characters less than 8
	Given User opens Register Page
	When User enters passwords with less than eight characters	
	|username|password|confirmPassword|
	|NumNin@gmail.com|Test234|Test234|
	
	Then User gets error message "password_mismatch:The two password fields didn’t match."
@TC007	
	Scenario: User gets error message when entered password with only numbers
	Given User opens Register Page
	When User enters passwords with only numbers
	|username|password|confirmPassword|
	|NumNin@gmail.com|12345678|12345678|
	Then User gets error message "password_mismatch:The two password fields didn’t match."
@TC008	
Scenario: User gets redirected to HomePage successfully with valid login and passwords
	Given User opens Register Page
	When User enters valid username, password, confirmPassword and clicks on Register
	|username|password|confirmPassword|
	|NumNi17@gmail.com|Tester12345|Tester12345|
	
	Then The user should be redirected to Homepage with the message "New Account Created. You are logged in as <username>"
	
	|NumNi17@gmail.com|
