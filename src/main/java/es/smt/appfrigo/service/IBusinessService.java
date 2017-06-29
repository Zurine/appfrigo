package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.ProductBusinessDTO;
import es.smt.appfrigo.model.ProductCompositionDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMiniDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IBusinessService {

	public ResponseDTO get(int businessId,boolean data, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(BusinessDTO business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(BusinessDTO business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listMini(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getSellers(int businessId, TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO listProducts(int businessId, TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO listProductsMini(int businessId, TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO addBeacon(int businessId,List<Integer> beacon, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO removeBeacon(int businessId,int beaconId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addProduct(List<ProductBusinessDTO> product, int businessId,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO editProduct(ProductBusinessDTO product, int businessId,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activateProduct(boolean state, int productId, int businessId,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getProduct(int businessId, int productId,boolean data,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listBusinessType(int state, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getBusinessType(int id,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getBusinessIva(int business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getProductComposition(int product, int business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addSaleByAdmin(List<SaleMiniDTO> sales, int seller, int business, Date date, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getBeacons(int businessId, TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO getPromotions(int businessId, int productId,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listEquipment(int state, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int businessId,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deleteProduct(int businessId, int productId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO setSeller(List<Integer> seller, int businessId, TokenDTO t) throws JsonProcessingException, IOException;
	
	
	public ResponseDTO productsByEquipmentType(int type, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addProductToEquipmentType(int type, List<ProductCompositionDTO> list ,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deleteProductToEquipmentType(int type, int productId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listTaxes(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deleteSale(int saleId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO updateProducts(List<ProductDTO> products, int business, TokenDTO t) throws JsonProcessingException, IOException;
	
	
}
