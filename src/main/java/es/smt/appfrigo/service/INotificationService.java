package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface INotificationService {

	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO listApp(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id,boolean users, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(Notification u, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(Notification u, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO send(int id,List<Integer> users,List<Integer> conditions, int type, TokenDTO t) throws JsonProcessingException, IOException;
}
