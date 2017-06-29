package es.smt.appfrigo.converter;
//package es.smt.appfrigo.converter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import es.smt.appfrigo.bean.Location;
//import es.smt.appfrigo.bean.WorkDayRelation;
//import es.smt.appfrigo.model.WorkDayDTO;
//import es.smt.appfrigo.model.WorkDayRelationDTO;
//import es.smt.appfrigo.utils.MathUtils;
//
//public class WorkDayConverter {
//
//	private static WorkDayConverter instance;
//	
//	public WorkDayConverter(){}
//	
//	public static WorkDayConverter getInstance()
//	{
//		if (instance == null )
//		{
//			instance = new WorkDayConverter();
//		}
//		return instance;
//	}
//	
//
////	public WorkDayDTO dtoToBean(WorkDayDTO dto)
////	{
//////		WorkDay w = new WorkDay();
//////		w.setId(dto.getId());
////	//	w.setSeller(SellerConverter.getInstance().dtoMiniToBean(dto.getSeller()));
////	//	w.setBusiness(BusinessConverter.getInstance().dtoToBean(dto.getBusines()));
////		dto.setBoxState(dto.getBoxState());
//////		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//////		dto.setStringDate(dateFormat.format(dto.getwDate()));
//////		dto.setTime(MathUtils.round(dto.getTime(), 2));
//////		dto.setValue(dto.getValue());
////		dto.setLocation(new Location(dto.getLatitud(), dto.getLongitud()));
//////		dto.setPath(dto.isPath());
////		
////		return dto;
////	}
//	
////	
////	public List<WorkDayDTO> dtoToBeanList(List<WorkDayDTO> regions)
////	{
////		List<WorkDayDTO> list = new ArrayList<WorkDayDTO> ();
////		for(WorkDayDTO e: regions)
////		{
////			list.add(dtoToBean(e));
////		}
////		
////		return list;
////				
////	}
//	
////	public WorkDayRelation dtoToBean(WorkDayRelationDTO dto)
////	{
////		WorkDayRelation w = new WorkDayRelation();
////		w.setId(dto.getId());
////		w.setName(dto.getName());
////		w.setHours(MathUtils.round(dto.getHours(), 2));
////		w.setDays(dto.getDays());
////		w.setSales(MathUtils.round(dto.getSales(), 2));
////		w.setRelSales(MathUtils.round(dto.getSales() / dto.getDays(), 2));
////		w.setRelTime(MathUtils.round(dto.getHours() / dto.getDays(), 2));
////		
////		return w;
////	}
////	
////	
////	public List<WorkDayRelation> dtoRelToBeanList(List<WorkDayRelationDTO> dto)
////	{
////		List<WorkDayRelation> list = new ArrayList<WorkDayRelation> ();
////		for(WorkDayRelationDTO e: dto)
////		{
////			list.add(dtoToBean(e));
////		}
////		
////		return list;
////				
////	}
//}
