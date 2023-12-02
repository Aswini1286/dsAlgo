package stepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.SignInPage;
import page.common;

public class DataStructuresIntroduction_Steps {
	common commonfns = new common(DriverFactory.getDriver());
	SignInPage signInPage = new SignInPage(DriverFactory.getDriver());
	
	
	@When("User enters <username> and <password> in the signIn page")
	public void user_enters_username_and_password_in_the_sign_in_page(DataTable dataTable) {
		String userName = null;
		String passWord  = null;
		List<Map<String,String>> val = dataTable.asMaps();
		for(Map<String,String> e:val)
		{
			 userName = e.get("username");
			 passWord = e.get("password");
		}
		signInPage.enter_username(userName);
		signInPage.enter_password(passWord);

	}

	@When("User clicks on SignIn button")
	public void user_clicks_on_sign_in_button() {
	    signInPage.click_Login();
	}

	@Then("User is in the home page successfully")
	public void user_is_in_the_home_page_successfully() {
		commonfns.checkHomePage();				
	}

	@Given("User is in home page after login successfully")
	public void user_is_in_home_page_after_login_successfully() {
	    
		commonfns.checkHomePage();
	}

	@When("User clicks on GetStarted button below {string}")
	public void user_clicks_on_get_started_button_below(String string) {
	    commonfns.click_GetStart_DataStructures(string);
	}

	@Then("User is in the {string} page")
	public void user_is_in_the_page(String string) {
	    commonfns.checkTitleOfPage(string);
	}
	
	@Then("User clicks on each link in the page and checks {string} functionality with the python code")
	public void user_clicks_on_each_link_in_the_page_and_checks_functionality_with_the_python_code(String string) {

	  
	  commonfns.checkNoOfLinks(); 
	}

}
