package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface ISettingsService {

	public ResponseDTO getInativeTime(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO setInativeTime(int value, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getOfferTime(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO setOfferTime(int value, TokenDTO t) throws JsonProcessingException, IOException;
}
