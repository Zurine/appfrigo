package es.smt.appfrigo.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.ProductMicro;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.model.CompositionDTO;
import es.smt.appfrigo.model.ProductMiniDTO;

public class ProductManager {


	private static ProductManager instance;
	
	public ProductManager(){}
	
	public static ProductManager getInstance()
	{
		if (instance == null )
		{
			instance = new ProductManager();
		}
		return instance;
	}
	
	public SelectProduct getComposition(List<CompositionDTO> list,  SelectProduct product) throws JsonProcessingException, IOException
	{
		
	//	{
			//List<CompositionDTO> list = ParseJSON.getInstance().getCompositionDTOList(r.getResponse());
			List<CompositionDTO> compositions = new ArrayList<CompositionDTO>();
			product.setComponents(new ArrayList<List<Integer>>());
			for(CompositionDTO d : list)
			{
				CompositionDTO c = new CompositionDTO();
				c.setCategory(new Category(d.getCategory().getId(),d.getCategory().getName()));
				List<ProductMicro> minis = d.getProductMinis();
				c.setProductMinis(new ArrayList<ProductMicro>());
				for(ProductMicro p : minis){
					if(product.getProducts()==null || !product.getProducts().contains(p.getId()))
						c.getProductMinis().add(p);
				}
				
				compositions.add(c);
				product.getComponents().add(new ArrayList<Integer>());
//				if(compositions!=null && compositions.size()>0)
//					product.setComposition(compositions);
//				if(product.getComponents()!=null ) // && product.getComponents().size() < compositions.size()
//					product.getComponents().add(new ArrayList<Integer>());
			}
//			product.setComposition(compositions);
			if(compositions!=null && compositions.size()>0)
				product.setComposition(compositions);
		//}
		
		return product;
	}
	
	public String validateCompound(List<ProductMiniDTO> list)
	{
		String text = "";
		if(list==null ||list.size()<2)
			text = "Error compound product";
		
		return text;
			
	}
	
}
