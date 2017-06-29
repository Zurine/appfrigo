package es.smt.appfrigo.constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	
	private static PropertiesManager instance;
	
	public PropertiesManager() 
	{
	     super();
	}
	 
	public static PropertiesManager getInstance()
	{
		if (instance == null )
		{
			instance = new PropertiesManager();
		}
		return instance;
	}
	
	public String getProperty(String key) throws IOException
	{
		String result = "";
		InputStream inputStream = null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property value and print it out
			result = prop.getProperty(key);

 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
	
	
	
}
