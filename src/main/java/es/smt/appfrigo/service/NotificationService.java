package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class NotificationService implements INotificationService{

	private String get = "NotificationManagement.svc/json/GetNotification";
	private String add = "NotificationManagement.svc/json/NewNotifications";
	private String edit = "NotificationManagement.svc/json/EditNotification";
	private String delete = "NotificationManagement.svc/json/DeleteNotification";
	private String send = "NotificationManagement.svc/json/SendNotifications";
	private String list = "NotificationManagement.svc/json/notification/seller/list";
	private String listApp = "NotificationManagement.svc/json/notification/app/list";
	
	@Override
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);

        return r;
	}
	
	@Override
	public ResponseDTO listApp(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   listApp);

        return r;
	}

	@Override
	public ResponseDTO get(int id,boolean users, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"notificationId\":" + id + ",\"users\":" + users
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   get);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO add(Notification u, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"not\":" + new ObjectMapper().writer().writeValueAsString(u)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO edit(Notification u, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"notification\":" + new ObjectMapper().writer().writeValueAsString(u)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"notificationId\":" + id
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   delete);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO send(int id, List<Integer> users, List<Integer> conditions, int type, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"notificationId\":" +id  + ",\"useris\":" + new ObjectMapper().writer().writeValueAsString(users)
				+ ",\"conditions\":" + new ObjectMapper().writer().writeValueAsString(conditions)
				+ ",\"type\":" + type
	            + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		r = RestManager.getInstance().exchangePostWithString(request,   send);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}



}
