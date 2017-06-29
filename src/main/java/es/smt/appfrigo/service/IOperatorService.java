package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.ProductStock;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IOperatorService {
	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id,boolean data, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(Operator o, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(Operator o, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listCurrencies() throws JsonProcessingException, IOException;
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listProducts(int operatorId, boolean complete, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listProductStock(int operatorId, int productId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO updateStock(int stock, List<Integer> products, int operatorId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO updateProduct(ProductStock product, int operatorId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO resetStock(int operatorId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listDistributor(int operatorId, TokenDTO t) throws JsonProcessingException, IOException;
}
