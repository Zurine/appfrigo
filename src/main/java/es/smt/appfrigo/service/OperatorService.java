package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.ProductStock;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class OperatorService implements IOperatorService{

	
	private String list =  "OperatorManagement.svc/json/List";
	private String add = "OperatorManagement.svc/json/New";
	private String get = "OperatorManagement.svc/json/Get";
	private String edit = "OperatorManagement.svc/json/Edit";
	private String activate = "OperatorManagement.svc/json/Activate";
	private String currencies = "OperatorManagement.svc/json/ListCurrencies";
	private String delete = "OperatorManagement.svc/json/Delete";
	/************************stock*************************/
	private String listProducts = "OperatorManagement.svc/json/ListProducts";
	private String productStock = "OperatorManagement.svc/json/ProductStock";
	private String updateStock = "OperatorManagement.svc/json/UpdateStock";
	private String updateProduct = "OperatorManagement.svc/json/UpdateProduct";
	private String resetStock = "OperatorManagement.svc/json/ResetStock";
	private String listDistributor = "OperatorManagement.svc/json/ListDistributors";
	
	@Override
	public ResponseDTO list(int state,TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"state\":" + state
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
   
   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO get(int id,boolean data, TokenDTO t) throws JsonProcessingException, IOException {
		  
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"id\":" + id +",\"data\":" + data
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}

	@Override
	public ResponseDTO add(Operator o, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(o)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   add);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}

	@Override
	public ResponseDTO edit(Operator o,  TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(o)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}

	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"id\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listCurrencies() throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		r = RestManager.getInstance().exchangePostWithString("",   currencies);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		
		return r;
	}

	@Override
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id  + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   delete);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listProducts(int operatorId,boolean complete,  TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"operatorId\":" + operatorId  + ",\"complete\":" + complete + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   listProducts);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listProductStock(int operatorId, int productId, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"operatorId\":" + operatorId  + ",\"productId\":" + productId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   productStock);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO updateStock(int stock, List<Integer> products, int operatorId, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"stock\":" + stock   + ",\"products\":" +  new ObjectMapper().writer().writeValueAsString(products) + ",\"operatorId\":" + operatorId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request, updateStock);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO updateProduct(ProductStock product, int operatorId, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"product\":" + new ObjectMapper().writer().writeValueAsString(product)    + ",\"operatorId\":" + operatorId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request, updateProduct);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO resetStock(int operatorId, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"operatorId\":" + operatorId   +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request, resetStock);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listDistributor(int operatorId, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"operatorId\":" + operatorId   +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request, listDistributor);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

}
