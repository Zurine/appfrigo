package es.smt.appfrigo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import es.smt.appfrigo.constants.PropertiesManager;

public class ListManager {

	private static ListManager instance;
	
	public ListManager() 
	{
	     super();
	}
	 
	public static ListManager getInstance()
	{
		if (instance == null )
		{
			instance = new ListManager();
		}
		return instance;
	}
	
	public Map<Integer,String> getUserType() throws NumberFormatException, IOException{
		
		Map<Integer,String> list = new HashMap<Integer,String>();
		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.soyfrigo.admin")), "SoyFrigo Admin");
//		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.unilever.admin")), "Unilever Admin");
		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.operator")), "Operator");
		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.supervisor")), "Supervisor");
		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.msm")), "MSM");
//		list.put(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.appfrigo")), "AppFrigo");
		
		return list;
	}
	
}
