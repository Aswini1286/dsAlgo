package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterPage {
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(RegisterPage.class);

	String username =null;
	String password =null;
	String confirmPassword =null;
	//BY LOCATORS
	@FindBy(css="#id_username") 
	WebElement inp_Username;
	@FindBy(css="#id_password1")
	WebElement inp_password1;
	@FindBy(css="#id_password2")
	WebElement inp_password2;
	@FindBy(css="input[value='Register']")
	WebElement btn_Register;
	@FindBy(css=".alert")
	WebElement alert_errorMsg;
	@FindBy(css="div[role='alert']")
	WebElement alert_SuccessMsg;
	
	//CONSTRUCTORS
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//METHODS
	public void open_RegistrationPage()
	{
		driver.get("https://dsportalapp.herokuapp.com/register");
	}
	public void enterUserName(String userName)
	{
		inp_Username.sendKeys(userName);	
	}
	public void enterUserName(DataTable dataTable)
	{
		List<Map<String,String>> list = dataTable.asMaps(String.class,String.class);
	    for(Map<String,String> e: list)
			{
	    	  	
	    	 username = e.get("username");
	    	 
			}
	    inp_Username.sendKeys(username);
	}
	public void enterPassword(String password1)
	{
		inp_password1.sendKeys(password1);
	}
	public void enterPassword(DataTable dataTable)
	{
		List<Map<String,String>> list = dataTable.asMaps(String.class,String.class);
	    for(Map<String,String> e: list)
			{
	    	  	
	    	 password = e.get("password");
	    	 
			}
	    inp_password1.sendKeys(password);
	}
	public void enterConfirmPassword(String password2)
	{
		inp_password2.sendKeys(password2);
	}
	public void enterConfirmPassword(DataTable dataTable)
	{
		List<Map<String,String>> list = dataTable.asMaps(String.class,String.class);
	    for(Map<String,String> e: list)
			{
	    	  	
	    	 confirmPassword = e.get("confirmPassword");
	    	 
			}
	    
		inp_password2.sendKeys(confirmPassword);
	}
	public void ClickRegisterBtn()
	{
		btn_Register.click();	
	}
	public String capture_ToolTipError()
	{
		WebElement activeElement = driver.switchTo().activeElement();
		String msgStr = activeElement.getAttribute("validationMessage");
		System.out.println(msgStr);
		
		return msgStr ;
		
	}
	public String capture_alertMsgError()
	{
		return alert_errorMsg.getText();
	}
	public String verifyHomePage_Message()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));
		return alert_SuccessMsg.getText();
		
	}
}
