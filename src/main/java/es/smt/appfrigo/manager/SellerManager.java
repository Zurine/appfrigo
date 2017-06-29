package es.smt.appfrigo.manager;

import java.util.Iterator;
import java.util.List;

import es.smt.appfrigo.model.SellerDTO;

public class SellerManager {

	private static SellerManager instance;
	
	public SellerManager(){}
	
	public static SellerManager getInstance()
	{
		if (instance == null )
		{
			instance = new SellerManager();
		}
		return instance;
	}
	
	public List<SellerDTO> getActive(List<SellerDTO> list)
	{
		Iterator<SellerDTO> i = list.iterator();
		SellerDTO c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	public List<SellerDTO> getDeactive(List<SellerDTO> list)
	{
		Iterator<SellerDTO> i = list.iterator();
		SellerDTO c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	
	public List<SellerDTO> getNobusiness(List<SellerDTO> list)
	{
		Iterator<SellerDTO> i = list.iterator();
		SellerDTO c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.getBusiness()!= null && c.getBusiness().size()== 0)
			   i.remove();
		}
		
		return list;
	}
}
