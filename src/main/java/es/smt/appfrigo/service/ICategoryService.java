package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface ICategoryService {
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(Category u, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(Category u, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int categoryId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deleteCascade(int categoryId, TokenDTO t) throws JsonProcessingException, IOException;
}
