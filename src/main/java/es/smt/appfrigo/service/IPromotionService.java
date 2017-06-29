package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.model.OfferBeaconDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;

public interface IPromotionService {

	public ResponseDTO getList(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getDetails(int promotion,int business, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getOfferType(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO getConditions(TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO add(OfferDTO o, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO edit(OfferDTO o, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activate(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO desactivate(int id, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO asociatePromotion(List<OfferBeaconDTO> asignaciones, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO activateAssociation(int offerId, int beaconId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO deactivateAssociation(int offerId, int beaconId, TokenDTO t) throws JsonProcessingException, IOException;
	public ResponseDTO setWelcomeOffer(int offerId, TokenDTO t)throws JsonProcessingException, IOException;

}
