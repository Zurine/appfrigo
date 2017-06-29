package es.smt.appfrigo.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.smt.appfrigo.bean.Item;
import es.smt.appfrigo.bean.SalesStat;
import es.smt.appfrigo.bean.Traffic;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.SaleMicroDTO;
import es.smt.appfrigo.model.StatisticsDTO;
import es.smt.appfrigo.utils.MathUtils;

public class SalesConverter {

	private static SalesConverter instance;
	
	public SalesConverter(){}
	
	public static SalesConverter getInstance()
	{
		if (instance == null )
		{
			instance = new SalesConverter();
		}
		return instance;
	}
	

	public SalesStat dtoToBean(StatisticsDTO dto)
	{
		SalesStat s = new SalesStat();
		s.setItem(new Item(dto.getId(), dto.getName()));
		s.setTotal(MathUtils.round(dto.getTotal(),2)); 
		s.setWorkDay(dto.getTotalWorkDay());
		s.setTotalGifs(dto.getTotalGifs());
		if(dto.getTotalAmount()!=0 && dto.getTotalWorkDay()!=0)
			s.setDailyAmount((int) Math.round(MathUtils.round((double)dto.getTotalAmount() / dto.getTotalWorkDay(),1)));
		if(dto.getTotal()!=0 && dto.getTotalWorkDay()!=0)
			s.setDailyTotal(MathUtils.round(dto.getTotal() / dto.getTotalWorkDay(),2));
		s.setTotalHours(dto.getTotalHours());
		if(dto.getTotalHours()!=0&&dto.getTotalWorkDay()!=0)
			s.setDailyHours((int)dto.getTotalHours() / dto.getTotalWorkDay());
		s.setTraffic(dto.getTotalPeople());
		s.setTotalAmount(dto.getTotalAmount());
		if(dto.getTotalSales()!=0 && dto.getTotalPeople()!=0)
		{
			double[] arrayName = new double[10]; 
			arrayName[0] = (double)dto.getTotalSales() /dto.getTotalPeople();
			s.setPickUpRate(MathUtils.round((double)dto.getTotalSales() / dto.getTotalPeople(),2)); 
		}
		if(dto.getProducts()!=null && dto.getProducts().size()>0)
		{
			s.setSales(new ArrayList<SaleMicroDTO>());
			for(SaleMicroDTO sm:dto.getProducts())
			{
				s.getSales().add(sm);
			}
		}
			
		return s;
	}
	
	public List<SalesStat> dtoToBeanList(List<StatisticsDTO> sales)
	{
		List<SalesStat> list = new ArrayList<SalesStat> ();
		for(StatisticsDTO e: sales)
		{
			
			list.add(dtoToBean(e));
		}
		
		return list;
	}
	
	public Traffic dtoToTrafficBean(StatisticsDTO dto, int type)
	{
		Traffic t = new Traffic();
		t.setTotal(MathUtils.round(dto.getTotal(),2)); 
		t.setUnities(dto.getTotalAmount());
		
		t.setBusiness(new BusinessMiniDTO(dto.getBusinessId(), dto.getBusinessName()));
		t.setPeople(dto.getTotalPeople());
		if(t.getPeople()!=0 && t.getTotal()!=0  )
			t.setRate(MathUtils.round(t.getTotal() / t.getPeople(),2));
		else t.setRate(0);
		
		
		switch (type) {
	 		case 0:
	 		{
	 			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
				t.setLabel(dateFormat.format(dto.getDate()));
				
				break;
	 		}
		  case 1: 
		  {
			  Calendar c = Calendar.getInstance();
			  c.setFirstDayOfWeek(Calendar.MONDAY);
//			  c.setTime(dto.getDate());
			  int mondayNo = c.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_WEEK)+2;
			  c.set(Calendar.DAY_OF_MONTH,mondayNo);
			  Date d = c.getTime();
			  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			  t.setLabel(dateFormat.format(d));

			  break;
		  }
		  case 2:        
		  {
			  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			  t.setLabel(dateFormat.format(dto.getDate()));
			  
			  break;
		  }
		  case 3:        
		  {
			  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
			  t.setLabel(dateFormat.format(dto.getDate()));
			  
			  break;
		  }
		}
		
		if(type == 0) //Month
		{
			
		}
		else if(type == 1)
		{
			
		}
		
		return t;
	}
	
	
	public List<Traffic> dtoToTrafficBeanList(List<StatisticsDTO> sales, int type)
	{
		List<Traffic> list = new ArrayList<Traffic>();
		for(StatisticsDTO e: sales)
		{
			list.add(dtoToTrafficBean(e,type));
		}
		
		return list;
				
	}
	
}
