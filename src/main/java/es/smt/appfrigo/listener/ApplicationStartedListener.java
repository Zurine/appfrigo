package es.smt.appfrigo.listener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IOperatorService;

@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {

//	private static Map<Integer,String> userType = new HashMap<Integer,String>(); 
	
//	public Map<Integer,String> getUserType() {
//		return userType;
//	}
	
	private static Map<Integer,String> currencies; 
	
	public Map<Integer,String> getCurrencies() {
		return currencies;
	}

//	@Autowired
//	private IUserService userService;
	
	@Autowired
	private IOperatorService operatorService;

    public void onApplicationEvent(ContextRefreshedEvent event) {

		try {
			//Get Equipment types
//			ResponseDTO r = userService.listTypes();
//			if(r.getError().getCode() == Constants.codeOK)
//			{
//				List<DataDTO> list = ParseJSON.getInstance().getDataDTOList(r.getResponse());
//				userType = new HashMap<Integer,String>();
//				for (DataDTO i : list) userType.put(i.getId(),i.getName());
//			}
			
			
			
			ResponseDTO r = operatorService.listCurrencies();
			if(r.getError().getCode() == Constants.codeOK){
				List<Default> list = ParseJSON.getInstance().getDefaultDTOList(r.getResponse());
				currencies = new HashMap<Integer,String>();
				for (Default i : list) currencies.put(i.getId(),i.getName());
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 
}