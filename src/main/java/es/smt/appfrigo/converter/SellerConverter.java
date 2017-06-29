package es.smt.appfrigo.converter;

import es.smt.appfrigo.bean.Seller;
import es.smt.appfrigo.model.SellerMiniDTO;

public class SellerConverter {

	private static SellerConverter instance;
	
	public SellerConverter(){}
	
	public static SellerConverter getInstance()
	{
		if (instance == null )
		{
			instance = new SellerConverter();
		}
		return instance;
	}

//	public Seller dtoToBean(SellerDTO dto)
//	{
//		Seller s = new Seller();
//		s.setId(dto.getSellerId());
//		s.setName(dto.getName());
//		s.setSurname(dto.getSurname());
//		
////		if(s.getBusiness()!=null)
//			s.setBusiness(dto.getBusiness());
////		else s.setBusiness(new BusinessMiniDTO());
//		s.setUser(UserConverter.getInstance().dtoToMiniBean(dto.getUser().getUserId(),dto.getUser().getNickName(),
//				dto.getUser().getPassword(),dto.getUser().getEmail()));
//		s.setActive(dto.isActive());
//		
//		if(dto.getRegion()!=null)
//			s.setRegion(dto.getRegion());
//		if(dto.getUser()!=null)
//			s.setUser(UserConverter.getInstance().dtoToMiniBean(dto.getUser().getUserId(),"","",dto.getUser().getEmail()));
//		else s.setUser(new UserMini());
//		
//		return s;
//	}
	
	public Seller dtoMiniToBean(SellerMiniDTO dto)
	{
		Seller s = new Seller();
		s.setId(dto.getId());
		s.setUser(UserConverter.getInstance().dtoToMiniBean(dto.getUserId(),dto.getName()+" " + dto.getSurname(),"",""));
		s.setActive(dto.isActive());
		
		return s;
	}
	
//	public List<Seller> dtoToBeanList(List<SellerDTO> sellers)
//	{
//		List<Seller> list = new ArrayList<Seller> ();
//		for(SellerDTO s: sellers)
//		{
//			list.add(dtoToBean(s));
//		}
//		
//		return list;	
//	}
	
//	public SellerDTO beanToDto(Seller bean)
//	{
//		SellerDTO dto = new SellerDTO();
//		dto.setSellerId(bean.getId());
//		if(bean.getUser()!=null)
//		{
//			dto.setUser(new UserMiniDTO(bean.getUser().getUserId()));
//			dto.setName(bean.getName());
//			dto.setSurname(bean.getSurname());
//		//	dto.setUsername(bean.getUser().getNickName());
//		}
//		
//		if(bean.getBusiness()!=null)
//		{
//			dto.setBusiness(bean.getBusiness());
//		}
//		else dto.setBusiness(new BusinessMiniDTO());
//		dto.setActive(bean.isActive());
//		
//
//				
//		return dto;
//	}
}