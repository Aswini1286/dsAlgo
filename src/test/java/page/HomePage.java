package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.tools.sjavac.Log;

import factory.DriverFactory;
import appHooks.ApplicationHooks;

public class HomePage {
	
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(HomePage.class);

	// BY LOCATORS
	@FindBy(xpath="//a[@class='nav-link dropdown-toggle']") 
	WebElement lnk_DataStructures;
	
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement lnk_SignIn;
	
	@FindBy(css="a[href='/register']")
	WebElement lnk_Register;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement alert_NotLogged;
	
	
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='array']") 
	WebElement lnk_Array_GS;
	
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='data-structures-introduction']")
	WebElement lnk_DSIntro_GS;
	
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='linked-list']")
	WebElement lnk_linkedList_GS;
	
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='stack']")
	WebElement lnk_stack_GS;
	
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='queue']")
	WebElement lnk_queue_GS;

	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='tree']")
	WebElement lnk_tree_GS;
	@FindBy(xpath="//div[@class='card-body d-flex flex-column']//a[@href='graph']")
	WebElement lnk_graph_GS;

	// IN sign in page
	
	@FindBy(css="input[value='Login']")
	WebElement btn_Login;
	
	
	@FindBy(css="input[value='Register']")
	WebElement btn_Register;		
	//CONSTRUCTOR
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void check_HomePage()
	{
		
		
	logger.info("Opening home page...");
	//	DriverFactory.getDriver().get("https://dsportalapp.herokuapp.com/");
				
		if (lnk_SignIn.isDisplayed())
		{
			logger.info("USER IS IN HOME PAGE");
		}
		else
		{
			logger.error("UNABLE TO LOAD HOME PAGE");
		}
	}
	
	public void click_DropDownList()
	{
		logger.info("Clicking on data structures link");
		lnk_DataStructures.click();
	}
	public int check_DSDropDown()
	{
		//a[@class="dropdown-item"]
		return(driver.findElements(By.xpath("//a[@class=\"dropdown-item\"]")).size());
	}
	public void click_GetStartedOf(String link)
	{
		logger.info("Clicking on " +link+" link");
		driver.findElement(By.xpath("//div[@class='card-body d-flex flex-column']//a[@href='"+link+"']")).click();
		
	}
	public void click_DropDown_LinkItem(String linkItem)
	{
		lnk_DataStructures.click();
		//a[normalize-space()='Linked List']
		driver.findElement(By.xpath("//a[text()='"+linkItem+"']")).click();
	}
	public String check_NotLoggedAlert()
	{
		String alertText = alert_NotLogged.getText();
		return alertText;
	}
	
	public void click_SignIn()
	{
		logger.info("Clicking on sign in link...");
		lnk_SignIn.click();
	}
	public void check_SignInPage()
	{
		if(btn_Login.isDisplayed())
		{
			logger.info("USER IS IN SIGN IN PAGE");
			
		}
		else
		{
			logger.error("USER UNABLE TO LOAD SIGN IN PAGE");
		}
	}
	public void click_Register()
	{
		logger.info("Clicking on Register link...");
		lnk_Register.click();
	}
	public void check_RegisterPage()
	{
		if(btn_Register.isDisplayed())
		{
			logger.info("USER IS IN REGISTER PAGE");
			
		}
		else
		{
			logger.error("USER UNABLE TO LOAD REGISTER PAGE");
		}
	}

}

