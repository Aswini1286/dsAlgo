package runner;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/features"},
		glue={"stepDefinition","appHooks"},
		plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"pretty",
				"html:target/cucumber-reports/report.html",
				"json:target/cucumber-reports/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		
		
		
		
		)

public class runnertest {
	
}
