package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IRegionService {
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;

	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(Region r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(Region r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int regionId, TokenDTO t) throws JsonProcessingException, IOException;
}
