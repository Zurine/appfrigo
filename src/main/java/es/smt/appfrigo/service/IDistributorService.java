package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Contact;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IDistributorService {
	
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listByRegion(int regionId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(Distributor d, List<Contact> contacs, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(Distributor d, List<Contact> contacs, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int distributorId, TokenDTO t) throws JsonProcessingException, IOException;

}
