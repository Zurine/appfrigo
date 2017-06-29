package es.smt.appfrigo.converter;

import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.EquipmentBean;
import es.smt.appfrigo.model.EquipmentDTO;

public class EquipmentConverter {

	private static EquipmentConverter instance;
	
	public EquipmentConverter(){}
	
	public static EquipmentConverter getInstance()
	{
		if (instance == null )
		{
			instance = new EquipmentConverter();
		}
		return instance;
	}
	
	public EquipmentBean dtoToBean(EquipmentDTO dto)
	{
		return new EquipmentBean(dto.getEquipmentId(),dto.getDescription(),dto.getChannel() ,dto.isActive());
	}
	
	
	public List<EquipmentBean> dtoToBeanList(List<EquipmentDTO> equipments)
	{
		List<EquipmentBean> list = new ArrayList<EquipmentBean> ();
		for(EquipmentDTO e: equipments)
		{
			list.add(dtoToBean(e));
		}
		
		return list;
				
	}
	
	public EquipmentDTO beanToDto(EquipmentBean bean)
	{
		EquipmentDTO dto = new EquipmentDTO(bean.getEquipmentId(),bean.getDescription(),bean.getChannelBean(), bean.isActive());
				
		return dto;
	}
	
	
//	public EquipmentMiniBean dtoToBean(EquipmentMiniDTO dto)
//	{
//		if(dto!=null)
//			return new EquipmentMiniBean(dto.getEquipmentId(),dto.getDescription());
//		else return new EquipmentMiniBean();
//	}
//	
//	
//	public EquipmentMiniDTO beanToDto(EquipmentMiniBean bean)
//	{
//		EquipmentMiniDTO dto = new EquipmentMiniDTO(bean.getEquipmentId(),bean.getDescription());
//				
//		return dto;
//	}
	
}
