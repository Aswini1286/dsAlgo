package page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import page.HomePage;
import page.SignInPage;
import utilities.ExcelReader;

public class common {
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
	
	
	public common(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void click_GetStart_DataStructures(String DataStructure)
	{
		String path = "//a[@href='"+DataStructure+"']";
		driver.findElement(By.xpath(path)).click();
		//driver.findElement(By.xpath("//a[@href='data-structures-introduction']")).click();
		//driver.findElement(By.xpath("//a[@href='tree']")).click();
		//driver.findElement(By.xpath("//a[@href='array']")).click();
		//driver.findElement(By.xpath("//a[@href='linked-list']")).click();
		//driver.findElement(By.xpath("//a[@href='stack']")).click();
		//driver.findElement(By.xpath("//a[@href='queue']")).click();
		//driver.findElement(By.xpath("//a[@href='graph']")).click();
		
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
		
		/// Positive test data
		Actions action =new Actions(driver);
		 action.sendKeys(textBox_Code, "print \"hello\"").build().perform();
		 btn_Run.click();
		// Assert.assertTrue(output_code.getText().contentEquals("hello"),"Success");
		 if(output_code.getText().contentEquals("hello"))
			 logger.info("Try Here Link works as expected..."); 
		 else
			 logger.error("Try Here Link is not working as expected...");
		 driver.navigate().refresh();
		 
		 
		 ////Negative test data
		 action.sendKeys(textBox_Code, "print $hello").build().perform();
		 btn_Run.click();
		 
		// Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();
	        if(alertMessage.contains("Error"))
	   		 logger.info("Try Here Link works as expected..."); 
	        else
	   		  logger.error("Try Here Link is not working as expected...");
		 
		 //Assert.assertTrue(alertMessage.contains("syntaxError"),"Success");
		 
		 driver.switchTo().alert().accept();
		

		 
	
		/*
		 * ExcelReader reader = new ExcelReader(); List<Map<String,String>> testData =
		 * reader.getData(
		 * "/Users/aswi1/eclipse-workspace/dsAlgo/src/test/resources/Exceldata/testdata.xlsx",
		 * sheetName); String PythonCode = testData.get(rowNumber).get("PythonCode");
		 * String Result = testData.get(rowNumber).get("Result");
		 * System.out.println(PythonCode); System.out.println(Result);
		 * 
		 * action.sendKeys(textBox_Code, PythonCode).build().perform(); btn_Run.click();
		 * //Assert.assertTrue(output_code.getText().contentEquals("hello"));
		 * if(output_code.getText().contentEquals(Result))
		 * logger.info("Try Here Link works as expected..."); else
		 * logger.error("Try Here Link is not working as expected...");
		 */ 
		/////****************HARC CODED************************/////////
		/*
		 * action.sendKeys(textBox_Code, "print \"hello\"").build().perform();
		 * btn_Run.click();
		 * Assert.assertTrue(output_code.getText().contentEquals("hello"));
		 * if(output_code.getText().contentEquals("hello"))
		 * logger.info("Try Here Link works as expected..."); else
		 * logger.error("Try Here Link is not working as expected...");
		 */
	}
	
	public void checkNoOfLinks() 
	{
		WebElement ele = driver.findElement(By.xpath("//body/div[2]"));
		
		List<WebElement> links = ele.findElements(By.tagName("a"));
		for (WebElement lnk:links)
		{
			String link = lnk.getText();
			logger.info(link);
			String lnk_xpath ="//ul//a[text()='"+link+"']";		
			driver.findElement(By.xpath(lnk_xpath)).click();
			checkTitleOfPage(link);
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
