package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.ProductBusinessDTO;
import es.smt.appfrigo.model.ProductCompositionDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMiniDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class BusinessService implements IBusinessService{

	private String listSellers= "EquipmentManagement.svc/json/GetSellers";
	private String listProducts= "EquipmentManagement.svc/json/ListProducts";
	private String listProductsMini= "EquipmentManagement.svc/json/ListProductsMini";
	private String addBeacon= "BusinessManagement.svc/json/AsignBeacon2Business";
	private String removeBeaon ="BusinessManagement.svc/json/unasignBeacon2Business";
	private String addProduct ="EquipmentManagement.svc/json/AsignProduct";
	private String editProduct ="EquipmentManagement.svc/json/EditAsignProduct";
	private String getProduct ="EquipmentManagement.svc/json/GetBusinessProduct";
	private String activateProduct ="EquipmentManagement.svc/json/ActivateBusinessProduct";
	private String get = "BusinessManagement.svc/json/GetBusiness";
	private String activate = "BusinessManagement.svc/json/ActivateBusiness";
	private String listBusinessType = "BusinessManagement.svc/json/ListBusinessType";
	private String getBusinessType = "BusinessManagement.svc/json/GetBusinessType";
	private String add = "BusinessManagement.svc/json/NewBusiness";
	private String edit = "BusinessManagement.svc/json/editBusiness";
	
	private String listDetailed = "BusinessManagement.svc/json/ListBusinessDetails";
	private String listMini = "BusinessManagement.svc/json/ListBusinessSmall";
	private String getIva = "BusinessManagement.svc/json/GetBusinessIva";
	
	private String productComposition = "EquipmentManagement.svc/json/ProductComposition";
	private String addSale = "EquipmentManagement.svc/json/AddSaleByAdmin";
	
	private String listBeacons = "BusinessManagement.svc/json/ListBusinessBeacons";
	private String listEquipment = "BusinessManagement.svc/json/ListBusinessMini";
	private String delete = "BusinessManagement.svc/json/Delete";
	private String deleteProduct = "EquipmentManagement.svc/json/Delete";
	private String setSeller = "EquipmentManagement.svc/json/SetSeller";
	
	private String productsByType = "EquipmentManagement.svc/json/ListProductByEquipmentType";
	private String addByType = "EquipmentManagement.svc/json/AddProductToEquipmentType";
	private String deleteByType = "EquipmentManagement.svc/json/DeleteProductToEquipmentType";
	private String listTaxes = "BusinessManagement.svc/json/ListTaxes";
	private String deleteSale ="EquipmentManagement.svc/json/DeleteSale";
	private String updateProducts = "EquipmentManagement.svc/json/UpdateProducts";
	
	@Override
	public ResponseDTO list(int state,TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

    	String request = "{\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

    	r = RestManager.getInstance().exchangePostWithString(request,   listDetailed);
    	 
  		if(r==null)
 		   r = new ResponseDTO();
 		r.setError(ErrorManager.getInstance().CheckResult(r));
    	 
        return r;
	}

	
	@Override
    public ResponseDTO getSellers(int businessId, TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listSellers);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
    }
	
	@Override
    public ResponseDTO listProducts(int businessId, TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listProducts);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
                
    }
	
	@Override
    public ResponseDTO listProductsMini(int businessId, TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listProductsMini);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
                
    }
	
	@Override
    public ResponseDTO addBeacon(int businessId,List<Integer> beacon, TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"beacon\":" + beacon + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   addBeacon);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
    }

	@Override
	public ResponseDTO removeBeacon(int businessId, int beaconId, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"beaconId\":" + beaconId + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   removeBeaon);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}
	
	@Override
    public ResponseDTO addProduct(List<ProductBusinessDTO> product, int businessId,TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"productdto\":" + new ObjectMapper().writer().writeValueAsString(product) + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   addProduct);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
                
    }
	
	@Override
    public ResponseDTO activateProduct(boolean state, int productId, int businessId,TokenDTO t) throws JsonProcessingException, IOException
    {
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"state\":" +state  + ",\"productId\":" + productId + 
  	  			",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   activateProduct);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
                
    }

	@Override
	public ResponseDTO editProduct(ProductBusinessDTO product, int businessId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"productdto\":" + new ObjectMapper().writer().writeValueAsString(product) + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   editProduct);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO getProduct(int businessId, int productId, boolean data, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"productId\":" + productId + 
  	  			",\"businessId\":" + businessId + ",\"data\":" + data + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   getProduct);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO get(int businessId,boolean data, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessId\":" + businessId +",\"data\":" + data + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   get);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessId\":" + id + ",\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   activate);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO listBusinessType(int state, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listBusinessType);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	
	@Override
	public ResponseDTO add(BusinessDTO business, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessDto\":" + new ObjectMapper().writer().writeValueAsString(business) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   add);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO edit(BusinessDTO business, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessDto\":" + new ObjectMapper().writer().writeValueAsString(business) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   edit);
  	  
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
	
	@Override
	public ResponseDTO getBusinessIva(int business, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"business\":" + business +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   getIva);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO getProductComposition(int product, int business, TokenDTO t)
			throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"productId\":" + product + ",\"businessId\":" + business + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   productComposition);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO getBusinessType(int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request=  "{\"id\":" + id +",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   getBusinessType);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO addSaleByAdmin(List<SaleMiniDTO> sales, int seller, int business, Date date, TokenDTO t)
			throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"sales\":" +  new ObjectMapper().writer().writeValueAsString(sales)  + ",\"seller\":" + seller
  	  			+  ",\"date\":" + "\"/Date("+ date.getTime()+")/\""+ ",\"business\":" + business + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   addSale);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO getBeacons(int businessId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessId\":" + businessId +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listBeacons);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO getPromotions(int businessId, int productId, TokenDTO t)
			throws JsonProcessingException, IOException {
		return null;
	}


	@Override
	public ResponseDTO listEquipment(int state, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

    	String request = "{\"state\":" + state +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

    	r = RestManager.getInstance().exchangePostWithString(request,   listEquipment);
    	 
  		if(r==null)
 		   r = new ResponseDTO();
 		r.setError(ErrorManager.getInstance().CheckResult(r));
    	 
        return r;
	}


	@Override
	public ResponseDTO delete(int businessId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"id\":" + businessId +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   delete);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO deleteProduct(int businessId, int productId, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"businessId\":" +  businessId  + ",\"productId\":" + productId
  	  			+   ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   deleteProduct);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO setSeller(List<Integer> seller, int businessId, TokenDTO t)
			throws JsonProcessingException, IOException {
	   	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"seller\":" + new ObjectMapper().writer().writeValueAsString(seller) + ",\"businessId\":" + businessId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   setSeller);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO productsByEquipmentType(int type, TokenDTO t) throws JsonProcessingException, IOException {
	   	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"equipmenyTypeId\":" + type + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   productsByType);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO addProductToEquipmentType(int type, List<ProductCompositionDTO> list, TokenDTO t)
			throws JsonProcessingException, IOException {
	   	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"type\":" + type + ",\"list\":" + new ObjectMapper().writer().writeValueAsString(list) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   addByType);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO deleteProductToEquipmentType(int type, int productId, TokenDTO t)
			throws JsonProcessingException, IOException {
	   	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"type\":" + type+ ",\"productId\":" + productId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   deleteByType);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO listTaxes(TokenDTO t) throws JsonProcessingException, IOException {
	   	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listTaxes);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}
	
	@Override
	public ResponseDTO deleteSale(int saleId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"saleId\": " + saleId+  

	             ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   deleteSale);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}
	
	@Override
	public ResponseDTO updateProducts(List<ProductDTO> products, int business, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request=  "{\"products\":" +  new ObjectMapper().writer().writeValueAsString(products)  + ",\"business\":" + business +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,updateProducts);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}
	

}