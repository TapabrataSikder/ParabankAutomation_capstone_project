package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	public static String getProperty(String key) throws Exception{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("src/test/resources/config.properties");
		prop.load(file);
		return prop.getProperty(key);
	}
}