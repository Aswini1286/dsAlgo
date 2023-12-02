package stepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.SignInPage;
import utilities.ExcelReader;

public class SignInPage_Steps {
	
	SignInPage signInPage = new SignInPage(DriverFactory.getDriver());
	
	@Given("The user is in the Sign in page")
	public void the_user_is_in_the_sign_in_page() {
		signInPage.open_SignInPage();
	}

	@When("User clicks {string} button with all fields empty")
	public void user_clicks_button_with_all_fields_empty(String string) {
	 signInPage.enter_username("");
	 signInPage.enter_password("");
	 signInPage.click_Login();
	}

	@Then("It should display {string} tooltip below Username textbox.")
	public void it_should_display_tooltip_below_username_textbox(String string) {
	signInPage.capture_ToolTipError();
	
	}

	@When("User enters only username and clicks on {string} button")
	public void user_enters_only_username_and_clicks_on_button(String string,DataTable dataTable) {
		List<String> list = dataTable.asList();
		
		signInPage.enter_username(list.get(1));
		
		
	}

	@When("User enters only password and clicks on {string} button")
	public void user_enters_only_password_and_clicks_on_button(String string, DataTable dataTable) {
		List<String> list = dataTable.asList();
		signInPage.enter_password(list.get(1));
	}

	@When("User enters combination of username and password and clicks on {string} button from given sheetname {string} and rowNumber {int}")
	public void user_enters_combination_of_username_and_password_and_clicks_on_button_from_given_sheetname_and_row_number(String string1,String sheetName, Integer  rowNumber) throws InvalidFormatException, IOException {
	 ExcelReader reader = new ExcelReader();
	 //String filePath = System.getProperty("user.id");
	 //System.out.println(filePath);
	 List<Map<String,String>> testData = reader.getData("/Users/aswi1/eclipse-workspace/dsAlgo/src/test/resources/Exceldata/testdata.xlsx", sheetName);
	 String userName = testData.get(rowNumber).get("username");
	 String passWord = testData.get(rowNumber).get("password");
	 String exp_Result = testData.get(rowNumber).get("result");
	 System.out.println(userName+passWord+exp_Result);
	 signInPage.enter_username(userName);
	 signInPage.enter_password(passWord);
	 signInPage.click_Login();
	 String act_Result = signInPage.capture_alertMsg();
	 if (exp_Result.equals(act_Result))
		// System.out.println("Actual Result is "+act_Result+" - As expected");
	 	signInPage.logger.info("Actual Result is "+act_Result+" - As expected");
	 else
		// System.out.println("Actual Result is "+act_Result+" - Not as expected");
	 	signInPage.logger.error("Actual Result is "+act_Result+" - Not as expected");
	}


}
