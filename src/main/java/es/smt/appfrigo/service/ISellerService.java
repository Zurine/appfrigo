package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface ISellerService {
	public ResponseDTO list(int state,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(SellerDTO d, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id,  boolean data,TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(SellerDTO d, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO allocateSeller(List<SellerDTO> sellers, TokenDTO t)throws JsonProcessingException, IOException;

	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listByOperators(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listByBusiness(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addList(List<SellerDTO> d, TokenDTO t)throws JsonProcessingException, IOException;

}
