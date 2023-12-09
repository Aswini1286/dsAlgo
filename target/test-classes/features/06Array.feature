@Array

Feature: Array Link Verification

Background: User is logged into the application
Given The user is in the Sign in page
When User enters <username> and <password> in the signIn page
		|username|password|
		|NumNi@gmail.com|Tester12345|
And User clicks on SignIn button
Then User is in the home page successfully

Scenario Outline: User is successfully navigated to Array Page and checks all the links on the page

Given User is in home page after login successfully
When User clicks on GetStarted button below "array" 
Then User is in the "Array" page
And User clicks on each link in the page and checks "Try here" functionality with the python code

Given User is in Practice Question <1> and checks the editor with valid and invalid python code

Given User is in Practice Question <2> and checks the editor with valid and invalid python code 

Given User is in Practice Question <3> and checks the editor with valid and invalid python code	

Given User is in Practice Question <4> and checks the editor with valid and invalid python code


