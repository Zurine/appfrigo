package es.smt.appfrigo.manager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.smt.appfrigo.bean.PromotionBeacon;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.PromotionConverter;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.ErrorDTO;
import es.smt.appfrigo.model.HorarioDTO;
import es.smt.appfrigo.model.OfferBeaconDTO;
import es.smt.appfrigo.model.ResponseDTO;

public class EquipmentManager {

	private static EquipmentManager instance;
	
	public EquipmentManager(){}
	
	public static EquipmentManager getInstance()
	{
		if (instance == null )
		{
			instance = new EquipmentManager();
		}
		return instance;
	}
	
	public List<BusinessMiniDTO> getActive(List<BusinessMiniDTO> list)
	{
		Iterator<BusinessMiniDTO> i = list.iterator();
		BusinessMiniDTO c = null;
		
		while (i.hasNext()) {
		   c = i.next();
		   if(!c.isActive())
			   i.remove();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfferBeaconDTO> associatePromotion(PromotionBeacon promotion) throws JsonGenerationException, JsonMappingException, ParseException, IOException
	{
		List<OfferBeaconDTO> asignaciones = new ArrayList<OfferBeaconDTO>();
		
		ResponseDTO r = getTimeArea(promotion.getTimeZone());
		
		if(r.getError().getCode() == Constants.codeOK)
		{
			promotion.setFranjaHoraria((List<HorarioDTO>)r.getResponse());
			asignaciones.add(PromotionConverter.getInstance().beanToDtoBeacon(promotion));
		}
		
		
		return asignaciones;
	}
	
	 private ResponseDTO getTimeArea(String timezone) throws ParseException, JsonGenerationException, JsonMappingException, IOException
     {
		ResponseDTO r = new ResponseDTO();
        ErrorDTO e = new ErrorDTO(200, "");
        List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();
        String[] hour = null;
        HorarioDTO h = new HorarioDTO();
        timezone = timezone.replace("[", "");
        timezone = timezone.replace("]", "");
        timezone = timezone.replace("\"", "");
        
        String [] tokens = timezone.split(",");
          
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
  		String dateInString = "01-05-2000 ";
  		Date date = null;
          
          for (String s : tokens)
          {
              if (s != null && !s.equals(""))
              {
                  hour = s.split("-");
                  h = new HorarioDTO();
                 
                  date = sdf.parse(dateInString+hour[0]);
                  h.setHoraIni(date.getTime());
                  
                  Date date2 = sdf.parse(dateInString+hour[1]);
                  h.setHoraFin(date2.getTime());
                  
                  if(date.after(date2))
                  {
                	  e.setCode(1);
                	  e.setDesc("Time Zone End time must be after start time");
                	  break;
                  }
                  horarios.add(h);
              }
          }
          r.setResponse(horarios);

          r.setError(e);

          return r;
      }

}
