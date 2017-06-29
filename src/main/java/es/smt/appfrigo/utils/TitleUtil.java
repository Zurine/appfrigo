package es.smt.appfrigo.utils;

import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.model.ProductDTO;

public class TitleUtil {

private static TitleUtil instance;
	
	public TitleUtil(){}
	
	public static TitleUtil getInstance()
	{
		if (instance == null )
		{
			instance = new TitleUtil();
		}
		return instance;
	}
	
	
	public String setNotification(Notification o){
		String title = "";
		if(o.getId() == 0)
			title = "Create New Notification";
		else title = "Edit " + o.getTitle();
		
		return title;
	}
	
	public String setProduct(ProductDTO p){
		String title = "";
		if(p.getId() == 0)
			title = "Create New Product";
		else title = "Edit " + p.getName();
		
		return title;
	}
}
