package utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import page.HomePage;
import page.SignInPage;

public class ElementUtil {
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(HomePage.class);
	
	SignInPage signInPage = new SignInPage(driver);

	@FindBy(css="pre[role='presentation']")
	WebElement textBox_Code;
	@FindBy(css="button[type='button']")
	WebElement btn_Run;
	@FindBy(css="#output")
	WebElement output_code;
	
	@FindBy(xpath="//a[@href='data-structures-introduction']")
	WebElement dataStructures;
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
		
	}
	public void click_GetStart_DataStructures(String DataStructure)
	{
		
		//driver.findElement(By.xpath("//a[@href='data-structures-introduction']")).click();
		driver.findElement(By.xpath("//a[@href='tree']")).click();
	}
	public void checkHomePage()
	{		
	
	String homePageMsg = driver.findElement(By.cssSelector(".alert")).getText();

	
	if(homePageMsg.equals("You are logged in"))	
		logger.info("User is in Home Page as expected");
	else
		logger.error("User is not in Home Page as expected");
	}
	public void check_TryHereLink() 
	{
		
		driver.findElement(By.linkText("Try here>>>")).click();
		
		Actions action =new Actions(driver);
		action.sendKeys(textBox_Code, "print \"hello\"").build().perform();
		btn_Run.click();
		Assert.assertTrue(output_code.getText().contentEquals("hello"));
		if(output_code.getText().contentEquals("hello"))
			//System.out.println("Try Here Link works as expected...");
			logger.info("Try Here Link works as expected...");
		else
			//System.out.println("Try Here Link is not working as expected...");
		logger.error("Try Here Link is not working as expected...");
	}
	public void checkNoOfLinks() 
	{
		WebElement ele = driver.findElement(By.xpath("//body/div[2]"));
		
		List<WebElement> links = ele.findElements(By.tagName("a"));
		for (WebElement lnk:links)
		{
			String link = lnk.getText();
			//System.out.println(link);
			logger.info(link);
			
			//driver.findElement(By.tagName(link)).click();
			
			String lnk_xpath ="//a[normalize-space()='"+link+"']";
			
			driver.findElement(By.xpath(lnk_xpath)).click();
			if(driver.findElement(By.linkText("Try here>>>")).isDisplayed())
				check_TryHereLink();
			driver.navigate().back();
			driver.navigate().back();
			
			
		}
	}
	public void checkTitleOfPage(String title)
	{
		String expTitle = driver.getTitle();
		if(title.contains(expTitle))
			logger.info("Title is "+expTitle+" as expected");
		else
			logger.error("Title is "+expTitle+" - Not as expected");
		
	}
}
