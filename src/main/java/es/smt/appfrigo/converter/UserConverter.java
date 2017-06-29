package es.smt.appfrigo.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.smt.appfrigo.bean.User;
import es.smt.appfrigo.bean.UserMini;
import es.smt.appfrigo.model.UserDTO;

public class UserConverter {
	
	private static UserConverter instance;
	
	public UserConverter(){}
	
	public static UserConverter getInstance()
	{
		if (instance == null )
		{
			instance = new UserConverter();
		}
		return instance;
	}
	
	public User dtoToBean(UserDTO dto)
	{
		User bean = new User(dto.getUserId(), dto.getNickName(), dto.getPassword(), dto.getDeviceToken(),
				dto.getToken(), dto.getEmail(), 
				dto.getSex(), dto.getCP(), dto.getBirthDate(), "",
				dto.getEnterpriseId(), dto.getRol(), dto.isAdmin(), dto.isActive(), dto.getTokenDTO());
		
		if(dto.getRegistrationDate()!=null)
		{
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			bean.setRegistrationDate(df.format(dto.getRegistrationDate()));
		}
		
				
		return bean;
	}
	
	
//	public UserAdminDTO dtoAdminToBean(UserAdminDTO dto)
//	{
//		//UserAdmin bean = new UserAdmin();
//		//bean.setId(dto.getUserId());
//		//bean.setNickName(dto.getNickName());
//	//	bean.setEmail(dto.getEmail());
//	//	bean.setTokenDTO(new TokenDTO());
//	//	bean.setSuperadmin(dto.isSuperAdmin());
//	//	bean.setEnterpriseId(dto.getEnterpriseId());
//		//bean.setActive(dto.isActive());
////		
////		if(dto.getBusiness()!=null && dto.getBusiness().size() > 0)
////		{
//////			List<BusinessMiniBean> list = new ArrayList<BusinessMiniBean>();
////			dto.setBusiness(new ArrayList<BusinessMiniDTO>());
//////			BusinessMiniBean mini = null;
////			for(BusinessMiniDTO b : dto.getBusiness())
////			{
//////				 mini = BusinessConverter.getInstance().dtoToBean(b);
////				 list.add(mini);
////			}
////			dto.setBusiness(list);
////		}
////		else dto.setBusiness(new ArrayList<BusinessMiniBean>());
////		
//		return dto;
//	}
	
//	public List<UserAdminDTO> dtoAdminToBeanList(List<UserAdminDTO> users)
//	{
//		List<UserAdminDTO> list = new ArrayList<UserAdminDTO> ();
//		for(UserAdminDTO u: users)
//		{
//			list.add(dtoAdminToBean(u));
//		}
//		
//		return list;
//				
//	}
	
	public List<User> dtoToBeanList(List<UserDTO> users)
	{
		List<User> list = new ArrayList<User> ();
		for(UserDTO u: users)
		{
			list.add(dtoToBean(u));
		}
		
		return list;
				
	}
	
	public UserDTO beanToDto(User bean) throws ParseException
	{
		UserDTO dto = new UserDTO(bean.getUserId(), bean.getNickName(), bean.getPassword(), bean.getDeviceToken(),
				bean.getToken(), bean.getEmail(), 
				bean.getSex(), bean.getCP(), bean.getBirthDate(),null,
				bean.getEnterpriseId(), bean.getRol(), bean.isAdmin(), bean.isActive(), bean.getTokenDTO());
		
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dto.setRegistrationDate(bean.getRegistrationDate());
				
		return dto;
	}
	

//	public UserAdminDTO beanToDto(UserAdmin bean) throws ParseException
//	{
//		UserAdminDTO dto = new UserAdminDTO();
//		dto.setUserId(bean.getId());
//		dto.setEmail(bean.getEmail());
//		dto.setNickName(bean.getNickName());
//		dto.setBusiness(new ArrayList<BusinessMiniDTO>());
//		
//		if(bean.getBusiness()!=null && bean.getBusiness().size()!=0)
//		{
//			List<BusinessMiniDTO> list = new ArrayList<BusinessMiniDTO>();
//			BusinessMiniDTO mini = null;
//			for (BusinessMiniBean b : bean.getBusiness()) {
//				mini = BusinessConverter.getInstance().beanToDto(b);
//				list.add(mini);
//			}
//			dto.setBusiness(list);
//		}
//		
//		dto.setEnterpriseId(bean.getEnterpriseId());
//		dto.setPassword(bean.getPassword());
//		dto.setActive(bean.isActive());
//				
//		return dto;
//	}
	
	

	public UserMini dtoToMiniBean(UserDTO dto)
	{
		UserMini bean = new UserMini(dto.getUserId(), dto.getNickName(), dto.getPassword(), dto.getEmail());
				
		return bean;
	}

	public List<UserMini> dtoToMiniBeanList(List<UserDTO> users)
	{
		List<UserMini> list = new ArrayList<UserMini> ();
		for(UserDTO u: users)
		{
			list.add(dtoToMiniBean(u));
		}
		
		return list;
				
	}
	
	public UserMini dtoToMiniBean(int id, String nickname, String password, String email)
	{
		UserMini bean = new UserMini(id, nickname,password,email);
				
		return bean;
	}
	
}
