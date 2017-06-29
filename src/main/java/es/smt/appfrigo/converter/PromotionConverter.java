package es.smt.appfrigo.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.BeaconMini;
import es.smt.appfrigo.bean.Item;
import es.smt.appfrigo.bean.Promotion;
import es.smt.appfrigo.bean.PromotionBeacon;
import es.smt.appfrigo.model.ConditionDTO;
import es.smt.appfrigo.model.OfferBeaconDTO;
import es.smt.appfrigo.model.OfferBusinessDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.OfferDetailsDTO;
import es.smt.appfrigo.model.WelcomeOffer;
import es.smt.appfrigo.utils.ConditionUtil;

public class PromotionConverter {

	private static PromotionConverter instance;
	
	public PromotionConverter(){}
	
	public static PromotionConverter getInstance()
	{
		if (instance == null )
		{
			instance = new PromotionConverter();
		}
		return instance;
	}
	
	public Promotion dtoToBean(OfferDTO dto)
	{
		Promotion p = new Promotion();
		p.setId(dto.getOfferId());
		p.setName(dto.getName());
		p.setDescription(dto.getDescription());
		p.setUrl(dto.getUrl());
		p.setType(dto.getType());
		p.setActive(dto.isActive());
		p.setPrice(dto.getPrice());
		p.setRedimible(dto.isIsRedimible());
		p.setWelcome(dto.isIsWelcomeOffer());
//		List<Product> prod = new ArrayList<Product>();
//		List<Integer> prodId = new ArrayList<Integer>();
	/*	for (ProductMiniDTO pm : dto.getProducts()) {
			prod.add(ProductConverter.getInstance().dtoMiniToBean(pm));
			prodId.add(pm.getProductId());
		}*/
		List<Item> list = new ArrayList<Item>();
		List<Integer> selected = new ArrayList<Integer>();
		if(dto.getObjCondiciones()!=null && dto.getObjCondiciones().size() > 0)
		{
			for(ConditionDTO c:dto.getObjCondiciones())
			{
				if(c.getCondicionEdad()!=null && !c.getCondicionEdad().equals(""))
				{
					list.add(new Item(c.getCondicionId(),ConditionUtil.getInstance().getAgeText(c.getCondicionEdad())));
				}
				else if(c.getCondicionSexo()!=null && !c.getCondicionSexo().equals(""))
				{
					list.add(new Item(c.getCondicionId(),ConditionUtil.getInstance().getGenderText(c.getCondicionSexo())));
				}
				else if(c.getCondicionTemperatura()!=null && !c.getCondicionTemperatura().equals(""))
				{
					list.add(new Item(c.getCondicionId(),ConditionUtil.getInstance().getTemperatureText(c.getCondicionTemperatura())));
				}
				selected.add(c.getCondicionId());
			}
			p.setConditions(list);
		}
		//p.setProductsId(prodId);
		p.setSelectedConditions(selected);
		
		//p.setProducts(prod);
		
		return p;
	}
	
	
	public List<Promotion> dtoToBeanList(List<OfferDTO> offerts)
	{
		List<Promotion> list = new ArrayList<Promotion> ();
		for(OfferDTO p: offerts)
		{
			list.add(dtoToBean(p));
		}
		
		return list;
				
	}
	
	
	public PromotionBeacon dtoToBusiBean(OfferBusinessDTO dto)
	{
		PromotionBeacon pb = new PromotionBeacon();
		
		Promotion p = new Promotion();
		p.setId(dto.getOfferId());
		p.setName(dto.getName());
		pb.setPromotion(p);
		
		
		
		return pb;
	}
	
	
	
	public List<PromotionBeacon> dtoToBeanBusiList(List<OfferBusinessDTO> offerts)
	{
		List<PromotionBeacon> list = new ArrayList<PromotionBeacon> ();
		for(OfferBusinessDTO p: offerts)
		{
			list.add(dtoToBusiBean(p));
		}
		
		return list;
				
	}
	
	public OfferDTO beanToDto(Promotion bean)
	{
		OfferDTO dto = new OfferDTO();
		dto.setOfferId(bean.getId());
		dto.setName(bean.getName());
		dto.setDescription(bean.getDescription());
		dto.setType(bean.getType());
		dto.setUrl(bean.getUrl());
		dto.setActive(bean.isActive());
		dto.setPrice(bean.getPrice());
		dto.setIsRedimible(bean.isRedimible());
		if(bean.getSelectedConditions()!=null && bean.getSelectedConditions().size() > 0)
		{
			dto.setCondition(new ArrayList<Integer>());
			for(Integer i:bean.getSelectedConditions())
				dto.getCondition().add(i);
		}
		/*if(bean.getProducts()!=null && bean.getProducts().size() > 0)
		{
			List<ProductMiniDTO> p = new ArrayList<ProductMiniDTO>();
			for (Product prod : bean.getProducts()) {
				p.add(new ProductMiniDTO(prod.getId()));
			}
			dto.setProducts(p);
		}*/
		
		return dto;
	}
	
	public WelcomeOffer welcomeToDto(Promotion bean)
	{
		WelcomeOffer w = new WelcomeOffer();
		if(bean!=null)
			w.setOfferId(bean.getId());
		
		return w;
	}
	
	public Promotion welcomeToBean(WelcomeOffer dto)
	{
		Promotion w = new Promotion();
		w.setId(dto.getOfferId());
		
		return w;
	}
	
	public PromotionBeacon dtoDetailsToBean(OfferDetailsDTO dto)
	{
		PromotionBeacon bean = new PromotionBeacon();
		Promotion p = new Promotion();
		p.setId(dto.getOfferId());
		p.setName(dto.getName());
		p.setDescription(dto.getOfferDescription());
		p.setType(dto.getOfferType());
		p.setUrl(dto.getOfferURL());
		if(dto.getCode()!=null && !dto.getCode().equals(""))
			p.setRedimible(true);
		else p.setRedimible(false);
		BeaconMini b = new BeaconMini();
		b.setId(dto.getBeaconId());
		b.setName(dto.getBeaconName());
		bean.setBeacon(b);
		bean.setPromotion(p);
		bean.setCode(dto.getCode());
		bean.setActive(dto.isActive());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		bean.setFechaFin(dateFormat.format(dto.getFechaFin()));
		bean.setFechaIni(dateFormat.format(dto.getFechaIni()));
		bean.setMax(dto.getMax());
		bean.setMaxPerUser(dto.getMaxPerUser());
		bean.setMaxPerUserDay(dto.getMaxPerUserDay());
		
		
		return bean;
	}
	
	public List<PromotionBeacon> dtoDetailsToBeanList(List<OfferDetailsDTO> offerts)
	{
		List<PromotionBeacon> list = new ArrayList<PromotionBeacon> ();
		for(OfferDetailsDTO p: offerts)
		{
			list.add(dtoDetailsToBean(p));
		}
		
		return list;
				
	}
	
	
	public OfferBeaconDTO beanToDtoBeacon(PromotionBeacon bean) throws ParseException
	{
		OfferBeaconDTO dto = new OfferBeaconDTO();
		dto.setActive(bean.isActive());
		if(bean.getBeacon()!=null)
			dto.setBeaconId(bean.getBeacon().getId());
		if(bean.getPromotion()!=null)
			dto.setOfferId(bean.getPromotion().getId());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		dto.setFechaIni(dateFormat.parse(bean.getFechaIni()));
//		dto.setFechaFin(dateFormat.parse(bean.getFechaFin()));
		dto.setFechaIni(dateFormat.parse(bean.getFechaIni()).getTime());
		dto.setFechaFin(dateFormat.parse(bean.getFechaFin()).getTime());
		dto.setCode(bean.getCode());
		dto.setRedimible(bean.getPromotion().isRedimible());
		dto.setMax(bean.getMax());
		dto.setMaxPerUser(bean.getMaxPerUser());
		dto.setMaxPerUserDay(bean.getMaxPerUserDay());
		dto.setFranjaHoraria(bean.getFranjaHoraria());
		
		return dto;
	}
}
