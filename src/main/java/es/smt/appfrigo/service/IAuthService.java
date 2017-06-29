package es.smt.appfrigo.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IAuthService {

	public ResponseDTO login(TokenDTO t, String username, String password) throws JsonProcessingException, IOException;
	public ResponseDTO requestPassword(String email) throws JsonProcessingException, IOException;
	public ResponseDTO getDashboard(TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO getDashboard2(TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO getRates(int equipmentId, TokenDTO t)throws JsonProcessingException, IOException;
	public ResponseDTO resetPassword(int userId, String resetPassCode, String newPass)throws JsonProcessingException, IOException;
}
