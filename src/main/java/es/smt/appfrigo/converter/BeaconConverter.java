package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.Beacon;
import es.smt.appfrigo.model.BeaconDTO;

public class BeaconConverter {

	private static BeaconConverter instance;
	
	public BeaconConverter(){}
	
	public static BeaconConverter getInstance()
	{
		if (instance == null )
		{
			instance = new BeaconConverter();
		}
		return instance;
	}
	
	public Beacon dtoToBean(BeaconDTO dto)
	{
		Beacon bean = new Beacon();
		bean.setId(dto.getBeaconId());
		bean.setName(dto.getName());
		bean.setDescription(dto.getDescription());
		bean.setMajor(dto.getMajor());
		bean.setMinor(dto.getMinor());
		bean.setLocalizacion(dto.getLocalizacion());
		bean.setLatitud(dto.getLatitud());
		bean.setLongitud(dto.getLongitud());
		bean.setUid(dto.getUid());
		bean.setBusiness(dto.getBusiness());
		bean.setActive(dto.isActive());
		
		if(dto.getObjOffers()!=null)
		{
			bean.setPromotions(PromotionConverter.getInstance().dtoDetailsToBeanList(dto.getObjOffers()));
		}
		
		return bean;
	}

	
	public List<Beacon> dtoToBeanList(List<BeaconDTO> beacosn)
	{
		List<Beacon> list = new ArrayList<Beacon> ();
		for(BeaconDTO b: beacosn)
		{
			list.add(dtoToBean(b));
		}
		
		return list;
				
	}
	
	public BeaconDTO beanToDto(Beacon bean)
	{
		BeaconDTO dto = new BeaconDTO();
		dto.setBeaconId(bean.getId());
		dto.setName(bean.getName());
		dto.setDescription(bean.getDescription());
		dto.setMajor(bean.getMajor());
		dto.setMinor(bean.getMinor());
		dto.setLocalizacion(bean.getLocalizacion());
		dto.setLatitud(bean.getLatitud());
		dto.setLongitud(bean.getLongitud());
		dto.setUid(bean.getUid());
		dto.setBusiness(bean.getBusiness());
		dto.setActive(bean.isActive());
		
		return dto;
	}

}
