package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface ISupportService {

	ResponseDTO list(int state, TokenDTO t)throws IOException;
	ResponseDTO get(int messageId, TokenDTO t)throws IOException;
}
