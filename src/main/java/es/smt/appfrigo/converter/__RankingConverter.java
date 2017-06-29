package es.smt.appfrigo.converter;
//package es.smt.appfrigo.converter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import es.smt.appfrigo.bean.Item;
//import es.smt.appfrigo.bean.Ranking;
//import es.smt.appfrigo.model.BusinessMiniDTO;
//import es.smt.appfrigo.model.RankingDTO;
//import es.smt.appfrigo.utils.MathUtils;
//
//public class RankingConverter {
//
//	private static RankingConverter instance;
//	
//	public RankingConverter(){}
//	
//	public static RankingConverter getInstance()
//	{
//		if (instance == null )
//		{
//			instance = new RankingConverter();
//		}
//		return instance;
//	}
//	
////
////	public Ranking dtoToBean(RankingDTO dto)
////	{
////		Ranking s = new Ranking();
////		s.setSold(dto.getAmount());
////		s.setGift(dto.getGifts());
////		s.setTotal(MathUtils.round(dto.getTotal(),2));
////		s.setItem(new Item(dto.getId(), dto.getName()));
////		s.setBusiness(new BusinessMiniDTO(dto.getBusinessId(), dto.getBusinessName()));
////		
////		return s;
////	}
////	
////	
////	public List<Ranking> dtoToBeanList(List<RankingDTO> ranking)
////	{
////		List<Ranking> list = new ArrayList<Ranking>();
////		for(RankingDTO e: ranking)
////		{
////			list.add(dtoToBean(e));
////		}
////		
////		return list;
////				
////	}
//	
//
//}
