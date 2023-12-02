package appHooks;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import utilities.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	public String url;
	public String register_url;
	Properties prop;
	 
	
	@Before(order=0)
	public void getProperty()
	{
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	
	@Before(order=1)
	public void launchBrowser()
	{
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String register_url=prop.getProperty("register_url");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	//	DriverFactory.getDriver().get(url);
		
	}
	
	
	@After(order=0)
	public void quitBrowser()
	{
		driver.quit();
	}
	
	@After(order=1)
	public void tearDown(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			 byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			 scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
	@AfterStep
	public void afterstep(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			//LoggerLoad.error("Steps Failed , Taking Screenshot");
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			String screenshotName = scenario.getName().replaceAll(" ", "_");

			File target=new File(".\\screenshots\\"+screenshotName+".png");
			FileUtils.copyFile(source, target);
          }
	}
	
}
