package es.smt.appfrigo.service;

import java.io.IOException;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.model.UserAdminDTO;

public interface IUserService {

	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO details(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(UserAdminDTO u,List<Integer>business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(UserAdminDTO u, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO changePassword(String currentPass, String newPass, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listTypes() throws JsonProcessingException, IOException;
	public ResponseDTO delete(int userId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO editPassword(SellerDTO d, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO updateImage(String image, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getUserBusiness(int userId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO updateSetttings(int action, boolean state, TokenDTO t) throws JsonProcessingException, IOException;
}
