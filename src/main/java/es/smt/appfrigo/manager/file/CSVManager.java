package es.smt.appfrigo.manager.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class CSVManager {

	private static CSVManager instance;
	
	private static String folder = "resources/out/";
	
	public CSVManager(){}
	
	public static CSVManager getInstance()
	{
		if (instance == null )
		{
			instance = new CSVManager();
		}
		return instance;
	}
	
	
	public String generateCsvFile(String path,List<List<String>> data, long name)
	{
		String url = "";
		try
		{
			url = path + folder + name+".csv";
		    FileWriter writer = new FileWriter(url);
		    
		    
		    for(List<String> l:data)
		    {
		    	for(String s:l)
		    	{
		    		writer.append(s);
			    	writer.append(',');
		    	}
		    	writer.append('\n');
		    }
				
		    //generate whatever data you want
				
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
		//url =  name+".csv";

		return url;
	
	}
}
