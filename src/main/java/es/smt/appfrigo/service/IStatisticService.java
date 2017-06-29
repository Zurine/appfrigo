package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IStatisticService {

	public ResponseDTO getSales(List<Integer> business, List<Integer> channel,List<Integer> region, List<Integer> equipType ,List<Integer> product ,List<Integer> operator , boolean workDay, Date startDate, Date endDate,int type,  TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getWorkDay(int seller, int businessId, Date startDate, Date endDate, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getPath(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getWorkDayRelation(int business, String startDate, String endDate, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getSalesByOperator(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getProductsSales(int id,int type, int business,  Date startDate, Date endDate, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getTickets(List<Integer> business,  Date startDate, Date endDate, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getSaleProducts(int id, TokenDTO t) throws JsonProcessingException, IOException;


}
