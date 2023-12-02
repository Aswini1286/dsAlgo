package page;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(RegisterPage.class);

	
	@FindBy(css="#id_username")
	WebElement inp_username;
	@FindBy(css="#id_password")
	WebElement inp_password;
	@FindBy(css="input[value='Login']")
	WebElement btn_Login;
	@FindBy(css=".alert")
	WebElement alert_Error;
	public SignInPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void open_SignInPage()
	{
		driver.get("https://dsportalapp.herokuapp.com/login");
	}
	public void enter_username(String username)
	{
		inp_username.sendKeys(username);
	}
	public void enter_password(String password)
	{
		inp_password.sendKeys(password);
	}
	public void click_Login()
	{
		btn_Login.click();	
	}
	public String capture_alertMsg()
	{
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//String msg = wait.until(ExpectedConditions.visibilityOf(alert_Error)).getText();
		
		//return msg;
		return alert_Error.getText();
	}
	public String capture_ToolTipError()
	{
		WebElement activeElement = driver.switchTo().activeElement();
		String msgStr = activeElement.getAttribute("validationMessage");
		System.out.println(msgStr);
		
		return msgStr ;
		
	}
}
