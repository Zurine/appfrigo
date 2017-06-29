package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IEnterpriseService {

	 public ResponseDTO getEnterpriseList(TokenDTO t) throws JsonProcessingException, IOException;
	 public ResponseDTO listEnterprises(TokenDTO t) throws JsonProcessingException, IOException;
}
