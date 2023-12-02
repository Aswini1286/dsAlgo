package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PortalPage {
	public WebDriver driver;
	public String url;
	
	private static final Logger logger = LogManager.getLogger(PortalPage.class);
	//BY LOCATORS
	
	@FindBy(xpath="//button[@class='btn']") WebElement Btn_GetStarted;
	
	
	//CONSTRUCTOR
	
	public PortalPage(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements

        PageFactory.initElements(driver, this);
	}
	//METHODS
	public void open_dsAlgoBrowser()
	{
		//WebDriver driver = new ChromeDriver();
		//DriverFactory.getDriver().get(url);
		logger.info("Opening Portal Page...");
		driver.get("https://dsportalapp.herokuapp.com/");
	}
	public void getStarted_Click()
	{
		logger.info("Clicking Get started button....");
		Btn_GetStarted.click();
		
		
	}
}
