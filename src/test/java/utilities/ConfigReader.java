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
	
}
