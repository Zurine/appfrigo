package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IProductService {
	public ResponseDTO list(int state,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(ProductDTO p, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getStock(int productId,int businessId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
//	public ResponseDTO updateStock(StockDTO s,int businessId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(ProductDTO p, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int productId,boolean data,  TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listMini(TokenDTO t) throws JsonProcessingException, IOException;
//	public ResponseDTO listChildren(int productId, boolean parent,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listCategories(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listSimple(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listComposed(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listByCategories(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int productId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deleteCascade(int productId, TokenDTO t) throws JsonProcessingException, IOException;

}
