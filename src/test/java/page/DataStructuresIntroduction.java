package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DataStructuresIntroduction {
	public WebDriver driver;
	common commonfns = new common(driver);
	
	public DataStructuresIntroduction(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

}
