package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.model.VoucherDTO;

public interface IVoucherService {

	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(VoucherDTO v, List<Integer> retails,  TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(VoucherDTO v, List<Integer> retails, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addRetail(int id, List<Integer> retails, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO send(int voucherId, List<Integer> age, List<Integer> gender, List<String> cp, List<Integer> users, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getConditiosn(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO addVoucherCodes(List<String> codes, int voucherId, TokenDTO t) throws JsonProcessingException, IOException;
}
