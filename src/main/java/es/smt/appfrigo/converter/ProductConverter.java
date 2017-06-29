package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ProductMicro;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.model.CompositionDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ProductMiniDTO;

public class ProductConverter {
	
	private static ProductConverter instance;
	
	public ProductConverter(){}
	
	public static ProductConverter getInstance()
	{
		if (instance == null )
		{
			instance = new ProductConverter();
		}
		return instance;
	}
	
	
	public ProductDTO dtoToBean(ProductDTO dto)
	{
//		Product p = new Product();
//		p.setId(dto.getId());
//		p.setName(dto.getName());
//		p.setDescription(dto.getDescription());
//		p.setPrice(dto.getPrice());
//		p.setActive(dto.isActive());
//		if(dto.getCategory()!=null)
//			p.setCategory(new Item(dto.getCategory().getId(),dto.getCategory().getName()));
//		else p.setCategory(new Item());
//		p.setComposition(dto.getComposition());
		if(dto.getComposition()!=null && dto.getComposition().size()>0)
			dto.setType(1);
//		if(dto.getComposition()!=null && dto.getComposition().size()>0)
//		{
//			p.setComposition(new ArrayList<Composition>());
//			p.setType(1);
//			for (CompositionDTO pc : dto.getComposition()) {
//				Composition c = new Composition();
//				c.setCategory(new Item(pc.getCategory().getId(), pc.getCategory().getName()));
//				c.setAmount(pc.getAmount());
//				c.setProducts(new ArrayList<ProductMini>());
//				if(pc.getProducts()!=null && pc.getProducts().size()>0)
//				{
//					for(ProductMiniDTO pm: pc.getProducts())
//					{
//						c.getProducts().add(new ProductMini(pm.getProductId(), pm.getName()));
//					}
//				}
//			
//				p.getComposition().add(c);
//			}
//		}
		dto.setImage(new Image(dto.getUrl()));
			
		return dto;
	}
	
	public ProductMiniDTO dtoToBeanMini(ProductDTO dto)
	{
		ProductMiniDTO p = new ProductMiniDTO();
		p.setId(dto.getId());
		p.setName(dto.getName());
		p.setPrice(dto.getPrice());
		p.setActive(dto.isActive());
//		if(dto.getComposed()>0)
//			p.setCompound(true);
//		p.setImage(new Image(dto.getImage()));
			
		return p;
	}
//	
//	public ProductDTO dtoMiniToBean(ProductMiniDTO dto)
//	{
//		return new Product(dto.getId(),dto.getName(),"",0.0,true,null,null);
//	}
//	
	public ProductMiniDTO dtoMiniToBeanMini(ProductMiniDTO dto)
	{
		ProductMiniDTO p = new ProductMiniDTO();
		p.setId(dto.getId());
		p.setName(dto.getName());
		p.setPrice(dto.getPrice());
		p.setActive(dto.isActive());
//		p.setGenericId(dto.getGenericId());
//		p.setCompound(dto.isCompound());
		
		return p;
	}
	

	
	public List<ProductDTO> dtoToBeanList(List<ProductDTO> products)
	{
		List<ProductDTO> list = new ArrayList<ProductDTO> ();
		for(ProductDTO p: products)
		{
			list.add(dtoToBean(p));
		}
		
		return list;
				
	}
	
//	public List<ProductMiniDTO> dtoToBeanMiniList(List<ProductMiniDTO> products)
//	{
//		List<ProductMiniDTO> list = new ArrayList<ProductMiniDTO> ();
//		for(ProductMiniDTO p: products)
//		{
//			list.add(dtoMiniToBeanMini(p));
//		}
//		
//		return list;
//				
//	}
	
	
//	public ProductStock dtoToStockBean(ProductDTO dto)
//	{
//		ProductStock p = new ProductStock(dtoToBean(dto),dto.getStock(),dto.getMinStock(),0, new Date(),dto.isActive(), new BusinessMiniBean(),dto.getIva());
//				
//		if(dto.getCategory()!=null)
//			p.getProduct().setCategory(new Item(dto.getCategory().getId(),dto.getCategory().getName()));
//		else p.getProduct().setCategory(new Item());
//		p.getProduct().setGenericId(dto.getGenericId());
//		if(dto.getComposition()!=null && dto.getComposition().size()>0)
//		{
//			p.setType(1);
//			p.getProduct().setComposition(new ArrayList<CompositionDTO>());
//			for (CompositionDTO pc : dto.getComposition()) {
//				Composition c = new Composition();
//				c.setCategory(new Item(pc.getCategory().getId(), pc.getCategory().getName()));
//				c.setAmount(pc.getAmount());
//				c.setProducts(new ArrayList<ProductMini>());
//				for(ProductMiniDTO pm: pc.getProducts())
//				{
//					c.getProducts().add(new ProductMini(pm.getProductId(), pm.getName()));
//				}
//				p.getProduct().getComposition().add(c);
//			}
//		}
//		
//		return p;
//	}
	
	
	public SelectProduct dtoToSelectProductBean(ProductDTO dto)
	{
		SelectProduct p = new SelectProduct();
		p.setProduct(new ProductMiniDTO(dto.getId(), dto.getName(), dto.getPrice(),dto.getUrl(), dto.isActive()));
		p.setStock(dto.getStock());
		p.setMinStock(dto.getMinStock());
		p.setPrice(dto.getPrice());
		p.setIva(dto.getIva());
		p.setActive(dto.isActive());
		p.setProducts(new ArrayList<Integer>());
		p.getProducts().add(dto.getId());
		p.setSellable(dto.isSellable());
		if(dto.getComposition()!=null && dto.getComposition().size()>0)
		{
			List<List<Integer>> ids = new ArrayList<List<Integer>>();
			List<Integer> category = new ArrayList<Integer>();
			for(CompositionDTO c:dto.getComposition())
			{
				category = new ArrayList<Integer>();
				for(ProductMicro pro: c.getProductMinis())
				{
					category.add(pro.getId());
				}
				ids.add(category);
			}
			p.setComponents(ids);
		}
		
		return p;
	}
	
//	public ProductDTO stockBeanToDto(ProductStock bean)
//	{
//		ProductDTO p = new ProductDTO();
//		if(bean.getProduct()!=null)
//			p.setProductId(bean.getProduct().getId());
//		p.setActive(bean.isActive());
//		p.setPrice(bean.getProduct().getPrice());
//		p.setStock(bean.getStock());
//		p.setMinStock(bean.getMinStock());
//		p.setIva(bean.getIva());
//		
//		
//		
//		return p;
//	}
	
//	public List<ProductStock> dtoToStockBeanList(List<ProductDTO> stock)
//	{
//		List<ProductStock> list = new ArrayList<ProductStock> ();
//		for(ProductDTO s: stock)
//		{
//			list.add(dtoToStockBean(s));
//		}
//		
//		return list;	
//	}
	
	public ProductDTO beanToDto(ProductDTO bean)
	{
		ProductDTO dto = new ProductDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setDescription(bean.getDescription());
		dto.setPrice(bean.getPrice());
		dto.setActive(bean.isActive());
		if(bean.getCategory()!=null && bean.getCategory().getId()!=0)// && bean.getParent().getId()!=0
		{
			dto.setCategory(new Category(bean.getCategory().getId(),"",false));
		}
		else dto.setCategory(null);
		
		dto.setComposition(new ArrayList<CompositionDTO>());
		if(bean.getComposition()!=null && bean.getComposition().size()>0)
		{
			for (CompositionDTO p : bean.getComposition()) {
				if(p.getCategory()!=null && p.getCategory().getId()!=0)
				{
					CompositionDTO c = new CompositionDTO();
					c.setCategory(new Category(p.getCategory().getId(), "",false));
					c.setAmount(p.getAmount());
					dto.getComposition().add(c);
				}
			}
		}
		
		if(bean.getImage()!=null)
			dto.setUrl(bean.getImage().getPath());
				
		return dto;
	}
//	
//	public ProductStock dtoToBean(StockDTO dto)
//	{
//		ProductStock ps = new ProductStock();
//		ps.setProduct(new Product(dto.getProduct().getId(), dto.getProduct().getName()));
//		ps.setDate(dto.getDate());
//		ps.setStock(dto.getAmount());
//		ps.setType(dto.getType());
//		ps.setBusiness(new BusinessMiniBean(dto.getBusiness().getId(), dto.getBusiness().getName()));
//		
//		return ps;
//		
//	}
	
//	public StockDTO beanToDto(ProductStock bean)
//	{
//		StockDTO ps = new StockDTO();
//		ps.setProduct(new ProductMiniDTO(bean.getProduct().getId()));	
//		ps.setType(bean.getType());
//		ps.setAmount(bean.getStock());
//		
//		return ps;
//		
//	}
	
//	public List<ProductStock> dtoStockToBeanList(List<StockDTO> products)
//	{
//		List<ProductStock> list = new ArrayList<ProductStock> ();
//		for(StockDTO p: products)
//		{
//			list.add(dtoToBean(p));
//		}
//		
//		return list;
//				
//	}
}
