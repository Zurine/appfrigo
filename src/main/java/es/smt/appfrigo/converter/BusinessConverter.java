package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;


import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.BusinessMiniDTO;

public class BusinessConverter {

	private static BusinessConverter instance;
	
	public BusinessConverter(){}
	
	public static BusinessConverter getInstance()
	{
		if (instance == null )
		{
			instance = new BusinessConverter();
		}
		return instance;
	}
	
//	public BusinessDTO beanToDto(Business bean)
//	{
//		BusinessDTO dto = new BusinessDTO();
//		dto.setId(bean.getId());
//		dto.setNombreComercial(bean.getName());
//		dto.setNombreFiscal(bean.getNombreFiscal());
//		dto.setDesc(bean.getDesc());
//		dto.setUnileverId(bean.getUnileverId());
//		dto.setCif(bean.getCif());
//		dto.setActive(bean.isActive());
//		dto.setType(new BusinessTypeDTO(bean.getType().getId()));
//		dto.setChannel(bean.getChannel());
//		dto.setRegion(new RegionDTO(bean.getRegion().getId()));
//		dto.setDistributor(new DistributorDTO(bean.getDistributor().getId()));
//		dto.setEnterpriseId(bean.getEnterpriseId());
//		dto.setDireccion(bean.getDireccion());
//		dto.setLatitud(bean.getLatitud());
//		dto.setLongitud(bean.getLongitud());
//		dto.setCiudad(bean.getCiudad());
//		dto.setInactiveTime(bean.getInactiveTime());
//		dto.setNumberPlate(bean.getNumberPlate());
//		dto.setIva(bean.getIva());
//		dto.setPhoto(bean.getPhoto());
//		
//		return dto;
//	}
//	
//	public Business dtoToBean(BusinessDTO dto)
//	{
//		Business bean = new Business(dto.getId(), dto.getNombreFiscal(), dto.getNombreComercial(),
//				dto.getCif(), dto.getLatitud(), dto.getLongitud(), dto.getPhoto(), dto.getEnterpriseId(), 
//				dto.isActive(), dto.getDireccion(), dto.getCiudad(), dto.getUnileverId(), 
//				dto.getDesc(), new ChannelDTO(),new RegionDTO(),new BusinessType(), dto.getInactiveTime(),false,null,0,dto.getNumberPlate(),dto.getIva());
//		
//		if(dto.getChannel()!=null)
//			bean.setChannel(dto.getChannel());
//		
//		if(dto.getRegion()!=null)
//			bean.setRegion(dto.getRegion());
//		
//		if(dto.getType()!=null)
//			bean.setType(BusinessConverter.getInstance().dtoTypeToBean(dto.getType()));
//		
//		if(dto.getDistributor()!=null)
//			bean.setDistributor(dto.getDistributor());
//		
//		if(dto.getBeacon()!=null)
//			bean.setBeacon(BeaconConverter.getInstance().dtoToBeanList(dto.getBeacon()));
//		
//		return bean;
//	}
//	
//	public List<Business> dtoToBeanList(List<BusinessDTO> business)
//	{
//		List<Business> list = new ArrayList<Business> ();
//		for(BusinessDTO b: business)
//		{
//			list.add(dtoToBean(b));
//		}
//		
//		return list;
//				
//	}
//	
//	public Business dtoToBean(BusinessDetailDTO dto)
//	{
//		Business bean = new Business(dto.getId(), dto.getName(), dto.isActive(),
//				 dto.getDireccion(), dto.getUnileverId(),dto.isInactive(),dto.getTotalSeller());
//				
//		bean.setChannel(new ChannelDTO(dto.getChannel()));
//		bean.setRegion(new RegionDTO(dto.getRegion()));
//		bean.setDistributor(new DistributorDTO(dto.getDistributor()));
//		bean.setType(new BusinessType(dto.getEquipmentType()));
//		
//		return bean;
//	}
//	
//	public List<Business> dtoDetailsToBeanList(List<BusinessDetailDTO> business)
//	{
//		List<Business> list = new ArrayList<Business> ();
//		for(BusinessDetailDTO b: business)
//		{
//			list.add(dtoToBean(b));
//		}
//		
//		return list;
//				
//	}
	
//	public BusinessMiniDTO dtoToBean(BusinessMiniDTO dto)
//	{
//		BusinessMiniDTO bean = new BusinessMiniDTO();
//		if(dto!=null)
//			bean = new BusinessMiniDTO(dto.getId(),dto.getUnileverId(),dto.getName(),
//				dto.getChannel(),dto.isActive(), dto.isInactive());
//		
//		return bean;
//	}
//	
//	public BusinessMiniDTO dtoToMiniBean(BusinessDTO dto)
//	{
//		BusinessMiniDTO bean = new BusinessMiniDTO();
//		if(dto!=null)
//			bean = new BusinessMiniDTO(dto.getId(),dto.getUnileverId(),dto.getNombreComercial(),
//				"",dto.isActive(), false);
//		
//		return bean;
//	}
	
	public BusinessDTO miniBeanToDto(BusinessDTO dto)
	{
		BusinessDTO bean = new BusinessDTO();
		if(dto!=null)
			bean.setId(dto.getId());
		else dto = null;
		return bean;
	}
	
//	public BusinessMiniDTO beanToDto(BusinessMiniBean bean)
//	{
//		BusinessMiniDTO dto = new BusinessMiniDTO();
//		dto.setId(bean.getId());
//		
//		return dto;
//	}
	
	public List<BusinessMiniDTO> dtoToBean(List<BusinessMiniDTO> business)
	{
		List<BusinessMiniDTO> list = new ArrayList<BusinessMiniDTO>();
		for(BusinessMiniDTO b: business)
		{
			list.add(b);
		}
		
		return list;
	}
	
//	public BusinessType dtoTypeToBean(BusinessTypeDTO dto)
//	{
//		BusinessType bean = new BusinessType();
//		bean.setId(dto.getId());
//		bean.setName(dto.getDescription());
//		
//		return bean;
//	}
	
//	public List<BusinessType> dtoTypeToBeanList(List<BusinessTypeDTO> business)
//	{
//		List<BusinessType> list = new ArrayList<BusinessType> ();
//		for(BusinessTypeDTO b: business)
//		{
//			list.add(dtoTypeToBean(b));
//		}
//		
//		return list;
//	}
	
}