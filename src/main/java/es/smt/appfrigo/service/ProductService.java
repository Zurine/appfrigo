package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class ProductService implements IProductService {

	private String listStock = "ProductManagement.svc/json/GetStock";
	private String add = "ProductManagement.svc/json/newProduct";
	private String activate = "ProductManagement.svc/json/ActivateProduct";
//	private String updateStock = "ProductManagement.svc/json/UpdateStock";
	private String edit = "ProductManagement.svc/json/editProduct";
	private String get = "ProductManagement.svc/json/GetProduct";
	private String list = "ProductManagement.svc/json/ListProducts";
	private String listMini = "ProductManagement.svc/json/listProductsMini";
	private String listCategories= "ProductManagement.svc/json/ListCategories";
	private String listSimple = "ProductManagement.svc/json/ListProductsSimple";
	private String listComposed = "ProductManagement.svc/json/ListProductsComposed";
	private String listByCategories = "ProductManagement.svc/json/ListProductsByCategory";
	private String delete = "ProductManagement.svc/json/Delete";
	private String deleteCascade = "ProductManagement.svc/json/DeleteCascade";
	
	@Override
	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException {

	   	 ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
	   	 String request = "{\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   list);
		
		return r;
	}

	@Override
	public ResponseDTO getStock(int productId,int businessId, TokenDTO t) throws JsonProcessingException, IOException 
	{
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

	  	String request= "{\"productId\":" + productId + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
	    	
	
	  	r = RestManager.getInstance().exchangePostWithString(request,   listStock);
	  	
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	    return r;
	}

	@Override
	public ResponseDTO add(ProductDTO p, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

	  	String request= "{\"productdto\":" + new ObjectMapper().writer().writeValueAsString(p) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
	    	
	    	
	  	r = RestManager.getInstance().exchangePostWithString(request,   add);
	  	
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	    return r;
	}
	
	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"productId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
		
	}

//	@Override
//	public ResponseDTO updateStock(StockDTO s,int id, TokenDTO t) throws JsonProcessingException, IOException {
//
//		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
//		
//		String request = "{\"stockdto\":" + new ObjectMapper().writer().writeValueAsString(s) + ",\"businessId\":" + id
//             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
//	
//		r = RestManager.getInstance().exchangePostWithString(request,   updateStock);
//    	  
//		if(r==null)
//			r = new ResponseDTO();
//		r.setError(ErrorManager.getInstance().CheckResult(r));
//
//	   return r;
//	}

	@Override
	public ResponseDTO edit(ProductDTO p, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"productdto\":" + new ObjectMapper().writer().writeValueAsString(p)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO get(int productId, boolean data,  TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"productId\":" + productId + ",\"data\":" + data 
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listMini(TokenDTO t) throws JsonProcessingException, IOException {
	   	 ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	 	
	   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   listMini);
	   	 
	   	if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}

/*	@Override
	public ResponseDTO listChildren(int productId, boolean parent, TokenDTO t)throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"productId\":" + productId + ",\"parent\":" + parent
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   listChildren);
		  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}*/

	@Override
	public ResponseDTO listCategories(TokenDTO t) throws JsonProcessingException, IOException {
	   
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		 	
	   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   listCategories);
	   	 
	   	if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}

	@Override
	public ResponseDTO listSimple(TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	 	
	   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   listSimple);
	   	 
	   	if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}

	@Override
	public ResponseDTO listComposed(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	 	
	   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   listComposed);
	   	 
	   	if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
		
	}

	@Override
	public ResponseDTO listByCategories(TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	 	
	   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
	
	   	 r = RestManager.getInstance().exchangePostWithString(request,   listByCategories);
	   	 
	   	if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}

	@Override
	public ResponseDTO delete(int productId, TokenDTO t) throws JsonProcessingException, IOException {
		
		 ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"productId\":" + productId 
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   delete);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}
	
	@Override
	public ResponseDTO deleteCascade(int productId, TokenDTO t) throws JsonProcessingException, IOException {
		
		 ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"productId\":" + productId 
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   deleteCascade);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

}
