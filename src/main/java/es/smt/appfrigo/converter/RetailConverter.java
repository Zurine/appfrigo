package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;


import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.Retail;
import es.smt.appfrigo.bean.RetailLocation;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;

public class RetailConverter {

	private static RetailConverter instance;
	
	public RetailConverter(){}
	
	public static RetailConverter getInstance()
	{
		if (instance == null )
		{
			instance = new RetailConverter();
		}
		return instance;
	}
	
	public Retail dtoToBean(RetailDTO dto)
	{
		if(dto!=null)
		{
			Retail r = new  Retail(dto.getId(), dto.getName(), new Image(dto.getImage()));
			if(dto.getLocations()!=null && dto.getLocations().size()>0)
			{
				r.setLocations(new ArrayList<RetailLocation>());
				for(RetailLocationDTO rl:dto.getLocations())
				{
					r.getLocations().add(dtoToBean(rl));
					
				}
			}
			return r;
		}
		else return new Retail();
	}
	
	
	public RetailLocation dtoToBean(RetailLocationDTO dto)
	{
		return new RetailLocation(dto.getId(),dto.getName(),dto.getRetailId(),dto.getLat(),dto.getLon(),dto.getAddress(),dto.isActive());
	}
	
	public List<Retail> dtoToBeanList(List<RetailDTO> channels)
	{
		List<Retail> list = new ArrayList<Retail> ();
		for(RetailDTO e: channels)
		{
			list.add(dtoToBean(e));
		}
		
		return list;
	}
	
	public RetailDTO beanToDto(Retail bean)
	{
		RetailDTO dto = new RetailDTO(bean.getId(), bean.getName(), bean.getImage().getPath());
				
		return dto;
	}
	

	public RetailLocationDTO beanToDto(RetailLocation bean)
	{
		RetailLocationDTO dto = new RetailLocationDTO(bean.getId(), bean.getName(), bean.getLat(), bean.getLon(), bean.getAddress(),bean.getRetail().getId(),bean.isActive());
				
		return dto;
	}
}
