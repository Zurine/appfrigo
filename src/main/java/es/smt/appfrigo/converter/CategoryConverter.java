package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Image;

public class CategoryConverter {

private static CategoryConverter instance;
	
	public CategoryConverter(){}
	
	public static CategoryConverter getInstance()
	{
		if (instance == null )
		{
			instance = new CategoryConverter();
		}
		return instance;
	}
	
	public Category dtoToBean(Category dto)
	{
		dto.setImage(new Image(dto.getUrl()));
		return dto;
	}
	
	public List<Category> dtoToBeanList(List<Category> categories)
	{
		List<Category> list = new ArrayList<Category>();
		for(Category e: categories)
		{
			list.add(dtoToBean(e));
		}
		
		return list;
	}

	
}
