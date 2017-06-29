package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IUserAppService {

	public ResponseDTO getUserList(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getUserDeviceList(TokenDTO t) throws JsonProcessingException, IOException;
}
