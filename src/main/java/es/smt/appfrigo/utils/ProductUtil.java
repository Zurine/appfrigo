package es.smt.appfrigo.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.ProdBusMini;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ProductMiniDTO;

public class ProductUtil {


	private static ProductUtil instance;
	
	public ProductUtil(){}
	
	public static ProductUtil getInstance()
	{
		if (instance == null )
		{
			instance = new ProductUtil();
		}
		return instance;
	}
	
	public List<Category> removeProducts(List<ProdBusMini> myProducts, List<Category> products){
		ProductDTO p = null;
		
		Map<Integer, ProdBusMini> myList =	myProducts.stream().collect(Collectors.toMap(ProdBusMini::getId,
			                                              Function.identity()));

		for(Category c : products)
		{
			Iterator<ProductDTO> i = c.getProducts().iterator();
			while (i.hasNext()) {
				p = i.next(); 
				if(p.getComposition()!=null && p.getComposition().size()>0)
				{
					i.remove();
				}
				else{
					if(myList.containsKey(p.getId()))
						i.remove();
				}
			}
		}
		
		Category cat = null;
		Iterator<Category> j = products.iterator();
		while (j.hasNext()) {
			cat = j.next(); 
			if(cat.getProducts()!=null && cat.getProducts().size()==0)
				j.remove();
		}
		
		
		return products;
	}
	
	public List<Category> removeProductsType(List<ProductMiniDTO> myProducts, List<Category> products){
		ProductDTO p = null;
		
		Map<Integer, ProductMiniDTO> myList =	myProducts.stream().collect(Collectors.toMap(ProductMiniDTO::getId,
			                                              Function.identity()));

		for(Category c : products)
		{
			Iterator<ProductDTO> i = c.getProducts().iterator();
			while (i.hasNext()) {
				p = i.next(); 
				if(p.getComposition()!=null && p.getComposition().size()>0)
				{
					i.remove();
				}
				else{
					if(myList.containsKey(p.getId()))
						i.remove();
				}
			}
		}
		
		Category cat = null;
		Iterator<Category> j = products.iterator();
		while (j.hasNext()) {
			cat = j.next(); 
			if(cat.getProducts()!=null && cat.getProducts().size()==0)
				j.remove();
		}
		
		
		return products;
	}
	
	/**
	 * TODO
	 * @param products
	 * @param myProducts
	 * @return
	 */
	public List<ProductMiniDTO> getCompound(List<ProductMiniDTO> products, List<ProductMiniDTO> myProducts){

		ProductMiniDTO p = null;
		Iterator<ProductMiniDTO> i = products.iterator();
		while (i.hasNext()) {
			p = i.next(); 
			for (ProductMiniDTO s : myProducts) {
				if(s.getId() == p.getId())
				{
					i.remove();
					break;
				}
			}
		}
		
		return products;
	}
}
