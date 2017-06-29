package es.smt.appfrigo.manager;

import java.util.Iterator;
import java.util.List;

import es.smt.appfrigo.bean.Category;

public class CategoryManager {

	private static CategoryManager instance;
	
	public CategoryManager(){}
	
	public static CategoryManager getInstance()
	{
		if (instance == null )
		{
			instance = new CategoryManager();
		}
		return instance;
	}
	
	public List<Category> getActive(List<Category> list)
	{
		Iterator<Category> i = list.iterator();
		Category c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	public List<Category> getDeactive(List<Category> list)
	{
		Iterator<Category> i = list.iterator();
		Category c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	
}
