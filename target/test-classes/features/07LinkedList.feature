@LinkedList
Feature: LinkedList Link Verification

  Background: User is logged into the application
    Given The user is in the Sign in page
    When User enters <username> and <password> in the signIn page
      | username        | password    |
      | NumNi@gmail.com | Tester12345 |
    And User clicks on SignIn button
    Then User is in the home page successfully

  Scenario Outline: User is successfully navigated to LinkedList Page and checks all the links on the page
    Given User is in home page after login successfully
    When User clicks on GetStarted button below "linked-list"
    Then User is in the "Linked List" page
    And User clicks on each link in the page and checks "Try here" functionality with the python code
