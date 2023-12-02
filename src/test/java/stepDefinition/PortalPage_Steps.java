package stepDefinition;

import com.aventstack.extentreports.util.Assert;
import com.sun.tools.sjavac.Log;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.jfr.internal.Logger;
import page.HomePage;
import page.PortalPage;

public class PortalPage_Steps {
	
	PortalPage portalPage = new PortalPage(DriverFactory.getDriver());
	HomePage homePage = new HomePage(DriverFactory.getDriver());
	
	@Given("User opens DS Algo portal link")
	public void user_opens_ds_algo_portal_link() {
	    portalPage.open_dsAlgoBrowser();
		
	}

	@When("User clicks the Get Started button")
	public void user_clicks_the_button() {
		portalPage.getStarted_Click();
		//Log.info("Get Started button is clicked");
	}

	@Then("User should be redirected to homepage")
	public void user_should_be_redirected_to_homepage() {
		homePage.check_HomePage();
	}

}
