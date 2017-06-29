package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.OfferBeaconDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class PromotionService implements IPromotionService{

	private String get = "OfferManagement.svc/json/GetOfferData";
	private String getOfferType = "OfferManagement.svc/json/listOfferTypes";
	private String getConditions = "OfferManagement.svc/json/listConditions";
	private String add = "OfferManagement.svc/json/newOffer";
	private String edit = "OfferManagement.svc/json/editOffer";
	private String activate = "OfferManagement.svc/json/activateOffer";
	private String desactivate = "OfferManagement.svc/json/removeOffer";
	private String details = "OfferManagement.svc/json/GetOfferDetails";
	private String asociatePromotion = "OfferManagement.svc/json/asignOffer2Beacon";
	private String activateAssociation= "BeaconManagement.svc/json/ActivateOfferAsignation";
	private String deactivateAssociation = "BeaconManagement.svc/json/DesactivateOfferAsignation";
	private String welcome = "OfferManagement.svc/json/ChangeWelcomeOffer";
	private String list = "OfferManagement.svc/json/listOffers";
	
	@Override
	public ResponseDTO getList(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

        String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

        r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
        return r;
	}

	@Override
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"offerId\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   get);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO getOfferType(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   getOfferType);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
		
	}

	@Override
	public ResponseDTO getConditions(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   getConditions);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
		
	}

	@Override
	public ResponseDTO add(OfferDTO o, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"offerdto\":" + new ObjectMapper().writer().writeValueAsString(o) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO edit(OfferDTO o, TokenDTO t) throws JsonProcessingException, IOException {


		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"offerdto\":" + new ObjectMapper().writer().writeValueAsString(o) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO activate(int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"offerId\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
		
	}

	@Override
	public ResponseDTO desactivate(int id, TokenDTO t) throws JsonProcessingException, IOException {
	ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   desactivate);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO getDetails(int promotion,int business, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse(); 
		
		 String request = "{\"offerId\":" + promotion + ",\"businessId\":" + business + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
			r = RestManager.getInstance().exchangePostWithString(request,   details);
		    	  
			if(r==null)
			   r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

			return r;
	}
	
	@Override
	public ResponseDTO asociatePromotion(List<OfferBeaconDTO> asignaciones, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse(); 
		
		String request = "{\"asignaciones\":" + new ObjectMapper().writer().writeValueAsString(asignaciones) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   asociatePromotion);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO activateAssociation(int offerId, int beaconId, TokenDTO t) throws JsonProcessingException, IOException
	{
		ResponseDTO r = RestUtils.getInstance().InitializeResponse(); 
	
		String request = "{\"offerId\":" + offerId + ",\"beaconId\":" + beaconId+  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   activateAssociation);
    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO deactivateAssociation(int offerId, int beaconId, TokenDTO t)throws JsonProcessingException, IOException
	{
		ResponseDTO r = RestUtils.getInstance().InitializeResponse(); 
	
		String request = "{\"offerId\":" + offerId + ",\"beaconId\":" + beaconId+  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   deactivateAssociation);
    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO setWelcomeOffer(int offerId, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse(); 
		
		String request = "{\"offerId\":" + offerId + ",\"isWelcome\":true,\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   welcome);
    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}
}
