package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IRetailService {

	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(RetailDTO r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(RetailDTO r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addLocation(RetailLocationDTO r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO editLocation(RetailLocationDTO r, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getLocation(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
}
