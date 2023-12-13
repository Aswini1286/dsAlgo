package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	
	private static Properties prop;
	private final static String propertyFilePath = "./src/test/resources/config/config.properties";	
	
	public Properties init_prop()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	public static String getexcelfilepath() {
		String xlfilepath = prop.getProperty("excelDataPath");
		if(xlfilepath != null)
			return xlfilepath;
		else
			throw new RuntimeException("xlfilepath not specified in the Configuration.properties file.");
	}
	
	//*** Added By Aswini To get Json File Path************
	public static String getJsonfilepath1() {
		String jsonfilepath1 = prop.getProperty("jsonDataPath1");
		//System.out.println("jsonDataPath");
		if(jsonfilepath1 != null)
			return jsonfilepath1;
		else
			throw new RuntimeException("jsonDataPath not specified in the Configuration.properties file.");
	}
	public static String getJsonfilepath2() {
		String jsonfilepath2 = prop.getProperty("jsonDataPath2");
		//System.out.println("jsonDataPath");
		if(jsonfilepath2 != null)
			return jsonfilepath2;
		else
			throw new RuntimeException("jsonDataPath not specified in the Configuration.properties file.");
	}
	public static String getJsonfilepath3() {
		String jsonfilepath3 = prop.getProperty("jsonDataPath3");
		//System.out.println("jsonDataPath");
		if(jsonfilepath3 != null)
			return jsonfilepath3;
		else
			throw new RuntimeException("jsonDataPath not specified in the Configuration.properties file.");
	}
	public static String getJsonfilepath4() {
		String jsonfilepath4 = prop.getProperty("jsonDataPath4");
		//System.out.println("jsonDataPath");
		if(jsonfilepath4 != null)
			return jsonfilepath4;
		else
			throw new RuntimeException("jsonDataPath not specified in the Configuration.properties file.");
	}
	//*** Added By Aswini To get Json File Path************
}
