//package es.smt.appfrigo.converter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import es.smt.appfrigo.bean.Notification;
//import es.smt.appfrigo.model.NotificationDTO;
//
//public class ___NotificationConverter {
//
//	private static ___NotificationConverter instance;
//	
//	public ___NotificationConverter(){}
//	
//	public static ___NotificationConverter getInstance()
//	{
//		if (instance == null )
//		{
//			instance = new ___NotificationConverter();
//		}
//		return instance;
//	}
//	
//	public Notification dtoToBean(NotificationDTO dto)
//	{
//		Notification bean = new Notification();
//		bean.setId(dto.getNid());
//		bean.setTitle(dto.getT());
//		bean.setMessage(dto.getD());
//		bean.setPhoto(dto.getP());
//		bean.setType(dto.gettN());
//		if(dto.getODto()!=null)
//			bean.setPromotion(PromotionConverter.getInstance().welcomeToBean(dto.getODto()));
//		
//		return bean;
//	}
//	
//	
//	public List<Notification> dtoToBeanList(List<NotificationDTO> notifications)
//	{
//		List<Notification> list = new ArrayList<Notification> ();
//		for(NotificationDTO d: notifications)
//		{
//			list.add(dtoToBean(d));
//		}
//		
//		return list;
//				
//	}
//	
//	public NotificationDTO beanToDto(Notification bean)
//	{
//		NotificationDTO dto = new NotificationDTO();
//		dto.setNid(bean.getId());
//		dto.setT(bean.getTitle());
//		dto.setD(bean.getMessage());
//		dto.setP(bean.getPhoto());
//		dto.settN(bean.getType());
//		dto.setODto(PromotionConverter.getInstance().welcomeToDto(bean.getPromotion()));
//				
//		return dto;
//	}
//	
//}
