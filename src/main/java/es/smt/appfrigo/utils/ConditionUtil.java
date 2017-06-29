package es.smt.appfrigo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Item;
import es.smt.appfrigo.bean.NotificationSend;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.ConditionDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IPromotionService;

public class ConditionUtil {

	private static ConditionUtil instance;
	
	public ConditionUtil(){}
	
	public static ConditionUtil getInstance()
	{
		if (instance == null )
		{
			instance = new ConditionUtil();
		}
		return instance;
	}
	
	public NotificationSend getConditions(IPromotionService promotionService, TokenDTO t, NotificationSend ns) throws IOException
	{
		
		ResponseDTO r = promotionService.getConditions(t);
		List<Item> condAge = new ArrayList<Item>();
		List<Item> condTemp = new ArrayList<Item>();
		List<Item> condGender = new ArrayList<Item>();
		
		if(r.getError().getCode() == Constants.codeOK)
		{
			List<ConditionDTO> list = ParseJSON.getInstance().getConditionDTOList(r.getResponse());
            for (ConditionDTO c : list)
            {

                if (c.getCondicionEdad() != null && !c.getCondicionEdad().equals(""))
                {
                	Item cc = new Item();
                	cc.setId(c.getCondicionId());
                	cc.setName(c.getCondicionEdad());
                	cc.setName(cc.getName().replace("*", "Any Age "));
                	cc.setName(cc.getName().replace(">", "More than "));
                	cc.setName(cc.getName().replace("<", "Less than"));
                	condAge.add(cc);
                }
                else if (c.getCondicionTemperatura() != null && !c.getCondicionTemperatura().equals(""))
                {
                	Item cc = new Item();
                	cc.setId(c.getCondicionId());
                	cc.setName(c.getCondicionTemperatura());
                	cc.setName(cc.getName().replace("*", "Any Temperature "));
                	cc.setName(cc.getName().replace(">", "More than "));
                	cc.setName(cc.getName().replace("<", "Less than "));
                	condTemp.add(cc);
                }
                else
                {
                	Item cc = new Item();
                	cc.setId(c.getCondicionId());
                	cc.setName(c.getCondicionSexo());
                	cc.setName(cc.getName().replaceAll(Pattern.quote("*"), "Any Gender "));
                	cc.setName(cc.getName().replace("1", "Male "));
                	cc.setName(cc.getName().replace("2", "Female "));
                	cc.setName(cc.getName().replace("3", "Other "));
                	condGender.add(cc);
                }
            }
            
            ns.setListAge(condAge);
            ns.setListTemperature(condTemp);
            ns.setListGender(condGender);
		}
		return ns;
	}
	
	public List<Item> getPromotionType()
	{
		List<Item> list = new ArrayList<Item>();
		list.add(new Item(1, "Web"));
		list.add(new Item(2, "Image"));
		list.add(new Item(3, "Videos"));
		
		return list;
	}
	
	public String  getAgeText(String text)
	{
		  text = text.replace("*", "Any Age");
		  text = text.replace(">", "More than");
		  text = text.replace("<", "Less than");
		  
		  return text;
	}
	
	public String  getGenderText(String text)
	{
		  text = text.replace("*", "Any Gender");
		  text = text.replace("1", "Male");
		  text = text.replace("2", "Female");
		  text = text.replace("3", "Other");
		  
		  return text;
	}
	
	public String  getTemperatureText(String text)
	{
		  text = text.replace("*", "Any Temperature");
		  text = text.replace(">", "More than");
		  text = text.replace("<", "Less than");
		  
		  return text;
	}
}
