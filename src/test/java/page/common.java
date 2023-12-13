package page;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import page.HomePage;
import page.SignInPage;
import utilities.ConfigReader;
import utilities.ExcelReader;


public class common {
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger(HomePage.class);
	
	SignInPage signInPage = new SignInPage(driver);
	ConfigReader conRead = new ConfigReader();
	
	

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
	public void practiceQuestion(int lnkNo) throws InterruptedException, IOException, ParseException
	{
		String filePath = null;
		String pracQuesUrl="https://dsportalapp.herokuapp.com/question/"+lnkNo+"";
		driver.get(pracQuesUrl);
		Actions action =new Actions(driver);
		//Thread.sleep(2000);
		
		//String filePath = "D:\\DSALGO\\dsAlgo\\src\\test\\resources\\JsonData\\code.json";
		if(lnkNo==1)
			 filePath = ConfigReader.getJsonfilepath1();
		else if (lnkNo==2)
			 filePath = ConfigReader.getJsonfilepath2();
		else if (lnkNo==3)
			filePath = ConfigReader.getJsonfilepath3();
		else if (lnkNo==4)
			filePath = ConfigReader.getJsonfilepath4();
		
			
		JSONArray List = jsonReaderData(filePath);
		
        for(int i=0;i<List.size();i++) {
            JSONObject data = (JSONObject) List.get(i);
           // System.out.println("Each block -> "+data);//This prints every block - one json object
            JSONObject PythonCodenRes = (JSONObject) data.get("PythonCodenRes");
            //System.out.println("Each data -> "+PythonCodenRes); //This prints each data in the block
            String codeLn1 =(String) PythonCodenRes.get("ln1");
            String codeLn2 =(String) PythonCodenRes.get("ln2");
            String codeLn3 =(String) PythonCodenRes.get("ln3");
            String codeLn4 =(String) PythonCodenRes.get("ln4");
            String codeLn5 =(String) PythonCodenRes.get("ln5");
            String codeLn6 =(String) PythonCodenRes.get("ln6");
            String codeLn7 =(String) PythonCodenRes.get("ln7");
            String codeLn8 =(String) PythonCodenRes.get("ln8");
            String codeLn9 =(String) PythonCodenRes.get("ln9");
            String codeLn10 =(String) PythonCodenRes.get("ln10");
            String codeLn11 =(String) PythonCodenRes.get("ln11");
            String codeLn12 =(String) PythonCodenRes.get("ln12");
            String resultExp =(String) PythonCodenRes.get("result");
            textBox_Code.click();//
            action.keyDown(Keys.CONTROL)
            .sendKeys("a").keyDown(Keys.DELETE)
            .keyUp(Keys.DELETE).keyUp(Keys.CONTROL).build().perform();
    		if(codeLn1!=null)
    			action.sendKeys(codeLn1).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn2!=null)
    			action.sendKeys(codeLn2).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn3!=null)
    			action.sendKeys(codeLn3).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn4!=null)
    			action.sendKeys(codeLn4).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn5!=null)
    			action.sendKeys(codeLn5).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn6!=null)
    			action.sendKeys(codeLn6).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn7!=null)
    			action.sendKeys(codeLn7).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn8!=null)
    			action.sendKeys(codeLn8).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn9!=null)
    			action.sendKeys(codeLn9).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn10!=null)
        	    action.sendKeys(codeLn10).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn11!=null)
        	    action.sendKeys(codeLn11).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		if(codeLn12!=null)
        	    action.sendKeys(codeLn12).keyDown(Keys.ENTER).keyUp(Keys.ENTER).keyDown(Keys.HOME).keyUp(Keys.HOME).build().perform();
    		//Thread.sleep(3000);
    		System.out.println(resultExp);
    		btn_Run.click();
    		//Thread.sleep(2000);
    			
    		if (resultExp.equalsIgnoreCase("error"))
    		{
    			checkAlertAndClose();
    			 
    			
    		}
    		else
    		{	
    			checkCodeOutput(resultExp);
    		}
            
        }    
		

    	
	}

	
	public  JSONArray jsonReaderData(String filePath) throws FileNotFoundException
	{
		
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(filePath);
		//Read JSON file
		Object obj = null;
		try {
			obj = jsonParser.parse(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray List = (JSONArray) obj;
		//System.out.println("Entire Json File "+List); //This prints the entire json file
        //System.out.println(List.size());
		return List;
        

	}
	public void checkAlertAndClose()
	{
		//System.out.println("INSIDE error loop");
		 String alertMessage= driver.switchTo().alert().getText();
		 driver.switchTo().alert().accept();
		 //System.out.println(alertMessage);
	       if(alertMessage.contains("Error"))
	        	//System.out.println("Alert error message is as expected..."+alertMessage); 
	       		logger.info("Alert error message is as expected..."+alertMessage);
	        else
	        	//System.out.println("Alert error message is not as expected...");
	       		logger.error("Alert error message is not as expected...");
	
	}
	public void checkCodeOutput(String resultExp)
	{
		if(output_code.getText().equals(resultExp))
		{
			//System.out.println("The output result is as expected - "+output_code.getText());
			logger.info("The output result is as expected - "+output_code.getText());
			
		}
		else
			logger.error("The output result is not as expected - "+output_code.getText());
	}
}
