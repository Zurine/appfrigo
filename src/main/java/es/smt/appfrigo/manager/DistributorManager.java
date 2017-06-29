package es.smt.appfrigo.manager;

import java.util.Iterator;
import java.util.List;

import es.smt.appfrigo.bean.Distributor;

public class DistributorManager {

	private static DistributorManager instance;
	
	public DistributorManager(){}
	
	public static DistributorManager getInstance()
	{
		if (instance == null )
		{
			instance = new DistributorManager();
		}
		return instance;
	}
	
	public List<Distributor> getActive(List<Distributor> list)
	{
		Iterator<Distributor> i = list.iterator();
		Distributor c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	public List<Distributor> getDeactive(List<Distributor> list)
	{
		Iterator<Distributor> i = list.iterator();
		Distributor c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.isActive())
			   i.remove();
		}
		
		return list;
	}
}
