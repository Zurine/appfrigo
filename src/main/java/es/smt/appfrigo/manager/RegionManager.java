package es.smt.appfrigo.manager;

import java.util.Iterator;
import java.util.List;

import es.smt.appfrigo.bean.Region;

public class RegionManager {

	private static RegionManager instance;
	
	public RegionManager(){}
	
	public static RegionManager getInstance()
	{
		if (instance == null )
		{
			instance = new RegionManager();
		}
		return instance;
	}
	
	public List<Region> getActive(List<Region> list)
	{
		Iterator<Region> i = list.iterator();
		Region c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	public List<Region> getDeactive(List<Region> list)
	{
		Iterator<Region> i = list.iterator();
		Region c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	
}
