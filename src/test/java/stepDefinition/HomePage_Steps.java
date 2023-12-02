package stepDefinition;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.HomePage;
import page.PortalPage;

public class HomePage_Steps {
	
	HomePage homePage = new HomePage(DriverFactory.getDriver());
	PortalPage portalpage = new PortalPage(DriverFactory.getDriver());
	
	
	@Given("User opens home page")
	public void user_opens_home_page() {
		portalpage.open_dsAlgoBrowser();
		portalpage.getStarted_Click();
		homePage.check_HomePage();
	}

	@When("User clicks Data Structures dropdown")
	public void user_clicks_data_structures_dropdown() {
	
		homePage.click_DropDownList();
	}

	@Then("User should see {int} different data structure entries in the dropdown")
	public void user_should_see_different_data_structure_entries_in_the_dropdown(Integer int1) {
		int items_DSDropDown = homePage.check_DSDropDown();
		if(items_DSDropDown==6)
		{
			homePage.logger.info("6 items available in the DataStructure dropdown");
		}
		else
		{
			homePage.logger.error("Expected 6 items not available in DataStructure dropdown");
		}
	}

	
	 @When("The user clicks any of the Get Started buttons below the data structures"
	  ) public void the_user_clicks_any_of_the_get_started_buttons_below_the_data_structures() {
		 homePage.click_GetStartedOf("array");
	 }
	  
	  @Then("It should alert the user with a message {string}") 
	  public void it_should_alert_the_user_with_a_message(String string) { 
		String alertMsg = homePage.check_NotLoggedAlert();
		if(string.equals(alertMsg))
		{
			homePage.logger.info(alertMsg +" is displayed as expected");
		}
		else
		{
			homePage.logger.error(alertMsg+" is not displayed as expected");
		}
	  }
	  
	
	  @When("The user clicks any of the data structures item from the dropDown list"
	  ) public void the_user_clicks_any_of_the_data_structures_item_from_the_drop_down_list() {
	    homePage.click_DropDown_LinkItem("Linked List"); 
	   }
	  
	
	  @When("User clicks on SignIn") 
	  public void user_clicks_on_sign_in() { //
		  homePage.click_SignIn();
	  }
	  
	  @Then("The user should be redirected to Sign in page") 
	  public void the_user_sould_be_redirected_to_sign_in_page() {
	  	homePage.check_SignInPage();
	  }
	  
	  @When("User clicks on Register") 
	  public void user_clicks_on_register() {
		homePage.click_Register();  
	  }
	  
	  @Then("The user should be redirected to Register page") 
	  public void the_user_should_be_redirected_to_register_page() { 
		  homePage.check_RegisterPage();
	  }
	  	
	 	 }
