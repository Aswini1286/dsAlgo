package stepDefinition;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import page.RegisterPage;

public class RegisterPage_Steps {
	
	RegisterPage registerPage = new RegisterPage(DriverFactory.getDriver());	
	@Given("User opens Register Page")
	public void user_opens_register_page() {
	 registerPage.open_RegistrationPage();   
	}

	@When("User clicks Register button with all fields empty")
	public void user_clicks_register_button_with_all_fields_empty() {
	 registerPage.ClickRegisterBtn();   
	}

	@Then("User gets error message  {string} tooltip below Username textbox.")
	public void user_gets_error_message_tooltip_below_username_textbox(String string) {
	 String str = registerPage.capture_ToolTipError();
	 if (str.equals(string))
	 {
		 registerPage.logger.info(str+ " message is displayed as expected");
	 }
	 else
		 registerPage.logger.error(str+ "message is not as expected");
	}
//##########################################################################//
	@When("User enters username and clicks on Register button")
	public void user_enters_username_and_clicks_on_register_button(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
		 registerPage.ClickRegisterBtn();   
	}

	@Then("User gets error message  {string} tooltip below Password textbox.")
	public void user_gets_error_message_tooltip_below_password_textbox(String string) {
		String str = registerPage.capture_ToolTipError();
		Assert.assertEquals(string, str);
	
		 if (str.equals(string))
		 {
			 registerPage.logger.info(str+ " message is displayed as expected");
			 //Assert.assertTrue(true);
		 }
		 else
		 {
			 registerPage.logger.error(str+ "message is not as expected");
			// Assert.assertFalse(false);
			 
		 }
	}

	@When("User enters username and password and clicks on Register button")
	public void user_enters_username_and_password_and_clicks_on_register_button(DataTable dataTable) {
	    registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@Then("User gets error message  {string} tooltip below Confirm Password textbox.")
	public void user_gets_error_message_tooltip_below_confirm_password_textbox(String string) {
		String str = registerPage.capture_ToolTipError();
		Assert.assertEquals(string, str);
		 if (str.equals(string))
		 {
			 registerPage.logger.info(str+ " message is displayed as expected");
		 }
		 else
			 registerPage.logger.error(str+ "message is not as expected");
	}

//################
	@When("User enters characters other than letters, digits and few special characters")
	public void user_enters_characters_other_than_letters_digits_and_few_special_characters(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.enterConfirmPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@Then("User gets error message {string}")
	public void user_gets_error_message(String string) {
		String str = registerPage.capture_alertMsgError();
		Assert.assertEquals(string, str);
		if (str.equals(string))
		 {
			 registerPage.logger.info(str+ " message is displayed as expected");
		 }
		 else
			 registerPage.logger.error(str+ "message is not as expected");
		
	}

	@When("User enters different passwords in Password and Password Confirmation field")
	public void user_enters_different_passwords_in_password_and_password_confirmation_field(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.enterConfirmPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@When("User enters passwords with less than eight characters")
	public void user_enters_passwords_with_less_than_eight_characters(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.enterConfirmPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@When("User enters passwords with only numbers")
	public void user_enters_passwords_with_only_numbers(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.enterConfirmPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@When("User enters valid username, password, confirmPassword and clicks on Register")
	public void user_enters_valid_username_password_confirm_password_and_clicks_on_register(DataTable dataTable) {
		registerPage.enterUserName(dataTable);
	    registerPage.enterPassword(dataTable);
	    registerPage.enterConfirmPassword(dataTable);
	    registerPage.ClickRegisterBtn();
	}

	@Then("The user should be redirected to Homepage with the message {string}")
	public void the_user_should_be_redirected_to_homepage_with_the_message(String string,DataTable dataTable) {
	    String str =registerPage.verifyHomePage_Message();
	    List<String> list=dataTable.asList();
	    String username = list.get(0);
	    System.out.println(str);
	    System.out.println(username);
	    System.out.println(str.contains(username));
	    Assert.assertTrue(str.contains(username), "Home page is displayed successfully with message "+str);
	    if(str.contains(username))
	    {
	    	registerPage.logger.info("Home page is displayed successfully with message "+str);
	    	
	    	
	    }
	    else
	    	registerPage.logger.error("Home page is not displayed successfully with message "+str);
	    	
	}



}
