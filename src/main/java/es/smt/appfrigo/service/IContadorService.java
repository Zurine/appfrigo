package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IContadorService {
	public ResponseDTO getContadorData(int type,String startDate, String endDate, TokenDTO t) throws JsonProcessingException, IOException;
}
