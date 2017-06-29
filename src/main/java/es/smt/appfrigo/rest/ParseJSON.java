package es.smt.appfrigo.rest;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.*;
import es.smt.appfrigo.model.BeaconDTO;
import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.BusinessDetailDTO;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.BusinessTypeDTO;
import es.smt.appfrigo.model.CompoundProductDTO;
import es.smt.appfrigo.model.ConditionDTO;
import es.smt.appfrigo.model.ContadorDTO;
import es.smt.appfrigo.model.DataDTO;
import es.smt.appfrigo.model.Home2DTO;
import es.smt.appfrigo.model.HomeDTO;
import es.smt.appfrigo.model.ListDTO;
import es.smt.appfrigo.model.LoginAdminDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.OfferDetailsDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ProductMiniDTO;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;
import es.smt.appfrigo.model.SaleMicroDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.StatisticsDTO;
import es.smt.appfrigo.model.UserAdminDTO;
import es.smt.appfrigo.model.UserDTO;
import es.smt.appfrigo.model.VoucherDTO;

public class ParseJSON {
	
	private static ParseJSON instance;
	
	public ParseJSON() 
	{
	     super();
	}
	
	public static ParseJSON getInstance()
	{
		if (instance == null )
		{
			instance = new ParseJSON();
		}
		return instance;
	}
	
	public String getString(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(String.class).readValue(s);
	}
	
	public List<String> getStringList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return new ObjectMapper().readValue(s, new TypeReference<List<String>>(){});
	}

	public UserDTO getUser(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return new ObjectMapper().reader(UserDTO.class).readValue(s);
	}
	
	public List<UserDTO> getUserList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return new ObjectMapper().readValue(s, new TypeReference<List<UserDTO>>(){});
	}
	
	public UserAdminDTO getUserAdminDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return new ObjectMapper().reader(UserAdminDTO.class).readValue(s);
	}
	
	public List<UserAdminDTO> getUserAdminDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return new ObjectMapper().readValue(s, new TypeReference<List<UserAdminDTO>>(){});
	}
	
	public BusinessDTO getBusinessDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BusinessDTO.class).readValue(s);
	}
	
	public Channel getChannelDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Channel.class).readValue(s);
	}
	
	public List<Channel> getChannelDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Channel>>(){});
	}
	
	public Region getRegionDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Region.class).readValue(s);
	}
	
	public List<Region> getRegionDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Region>>(){});
	}
	
	public Distributor getDistributorDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Distributor.class).readValue(s);
	}
	
	public List<Distributor> getDistributorDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Distributor>>(){});
	}

	public SellerDTO getSellerDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(SellerDTO.class).readValue(s);
	}
	
	public List<SellerDTO> getSellerDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<SellerDTO>>(){});
	}
	
	public ProductDTO getProductDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProductDTO.class).readValue(s);
	}
	
	public List<ProductDTO> getProductDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProductDTO>>(){});
	}
	
	public ProductMiniDTO getProductMiniDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProductMiniDTO.class).readValue(s);
	}
	
	public List<ProductMiniDTO> getProductMiniDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProductMiniDTO>>(){});
	}
	
	public BusinessMiniDTO getBusinessMiniDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BusinessMiniDTO.class).readValue(s);
	}
	
	public List<BusinessMiniDTO> getBusinessMiniDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BusinessMiniDTO>>(){});
	}
	
	public List<BusinessDetailDTO> getBusinessDetailsDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BusinessDetailDTO>>(){});
	}
	
	public BeaconDTO getBeaconDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BeaconDTO.class).readValue(s);
	}
	
	public List<BeaconDTO> getBeaconDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BeaconDTO>>(){});
	}
	
	public Notification getNotification(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Notification.class).readValue(s);
	}
	
	public List<Notification> getNotificationList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Notification>>(){});
	}
	
	public OfferDTO getOfferDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(OfferDTO.class).readValue(s);
	}
	
	public List<OfferDTO> getOfferDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<OfferDTO>>(){});
	}
	
	public OfferDetailsDTO getOfferDetailsDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(OfferDetailsDTO.class).readValue(s);
	}
	
	public List<OfferDetailsDTO> getOfferDetailsDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<OfferDetailsDTO>>(){});
	}
	
	public List<ConditionDTO> getConditionDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ConditionDTO>>(){});
	}

	public StatisticsDTO getStatisticsDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(StatisticsDTO.class).readValue(s);
	}
	
	public List<StatisticsDTO> getStatisticsDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<StatisticsDTO>>(){});
	}

	public BusinessTypeDTO getBusinessTypeDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BusinessTypeDTO.class).readValue(s);
	}
	
	public List<BusinessTypeDTO> getBusinessTypeDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BusinessTypeDTO>>(){});
	}
	
	public ContadorDTO getContadorDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ContadorDTO.class).readValue(s);
	}
	
	public List<ContadorDTO> getContadorDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ContadorDTO>>(){});
	}
	
	public LoginAdminDTO getLoginAdminDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(LoginAdminDTO.class).readValue(s);

	}
	
	public HomeDTO getHomeDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(HomeDTO.class).readValue(s);
	}
	
	
	public Home2DTO getHome2DTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Home2DTO.class).readValue(s);
	}
	
	public Category getCategoryDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Category.class).readValue(s);
	}
	
	public List<Category> getCategoryDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Category>>(){});
	}

	public RetailDTO getRetailDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(RetailDTO.class).readValue(s);
	}
	
	public List<RetailDTO> getRetailDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<RetailDTO>>(){});
	}
	
	
	public RetailLocationDTO getRetailLocatioDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(RetailLocationDTO.class).readValue(s);
	}
	
	public List<RetailLocationDTO> getRetailLocationDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<RetailLocationDTO>>(){});
	}
	
	public VoucherDTO getVoucherDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(VoucherDTO.class).readValue(s);
	}
	
	public List<VoucherDTO> getVoucherDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<VoucherDTO>>(){});
	}
	
	public ListDTO getListDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ListDTO.class).readValue(s);
	}
	
	public List<ListDTO> getListDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ListDTO>>(){});
	}
	
	public Operator getOperatorDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Operator.class).readValue(s);
	}
	
	public List<Operator> getOperatorDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Operator>>(){});
	}
	
	public DataDTO getDataDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(DataDTO.class).readValue(s);
	}
	
	public List<DataDTO> getDataDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<DataDTO>>(){});
	}
	
	public DataDTO getData(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Data.class).readValue(s);
	}
	
	public List<Data> getDataList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Data>>(){});
	}
	
	public List<Default> getDefaultDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Default>>(){});
	}
	
	public CompoundProductDTO getCompoundProductDTO(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(CompoundProductDTO.class).readValue(s);
	}
	
	public List<SaleMicroDTO> getSaleMicroDTOList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<SaleMicroDTO>>(){});
	}
	
	public boolean getBoolean(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Boolean.class).readValue(s);
	}
	
	public UserWorkDay getUserWorkDay(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(UserWorkDay.class).readValue(s);
	}
	
	public List<UserWorkDay> getUserWorkDayList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<UserWorkDay>>(){});
	}
	
	public BusinessNano getBusinessNano(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BusinessNano.class).readValue(s);
	}
	
	public List<BusinessNano> getBusinessNanoList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BusinessNano>>(){});
	}
	
	public UserTracking getUserTracking(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(UserTracking.class).readValue(s);
	}
	
	public List<UserTracking> getUserTrackingList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<UserTracking>>(){});
	}
	
	public TrackingDay getTrackingDay(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(TrackingDay.class).readValue(s);
	}
	
	public List<TrackingDay> getTrackingDayList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<TrackingDay>>(){});
	}
	
	public OperatorF getOperatorF(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(OperatorF.class).readValue(s);
	}
	
	public List<OperatorF> getOperatorFList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<OperatorF>>(){});
	}
	
	public BusinessF getBusinessF(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(BusinessF.class).readValue(s);
	}
	
	public List<BusinessF> getBusinessFList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<BusinessF>>(){});
	}
	
	public TicketDetail getTicketDetail(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(TicketDetail.class).readValue(s);
	}
	
	public List<TicketDetail> getTicketDetailList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<TicketDetail>>(){});
	}
	
	public ProductMicro getProductMicro(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProductMicro.class).readValue(s);
	}
	
	public List<ProductMicro> getProductMicroList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProductMicro>>(){});
	}
	
	public ProdBusDetail getProdBusDetail(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProdBusDetail.class).readValue(s);
	}
	
	public List<ProdBusDetail> getProdBusDetailList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProdBusDetail>>(){});
	}
	
	public ProdBusMini getProdBusMini(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProdBusMini.class).readValue(s);
	}
	
	public List<ProdBusMini> getProdBusMiniList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProdBusMini>>(){});
	}
	
	public ProductStock getProductStock(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(ProductStock.class).readValue(s);
	}
	
	public List<ProductStock> getProductStockList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<ProductStock>>(){});
	}
	
	public DistributorContact getDistributorContact(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(DistributorContact.class).readValue(s);
	}
	
	public List<DistributorContact> getDistributorContactList(Object bean) throws JsonGenerationException, JsonMappingException, IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<DistributorContact>>(){});
	}
	
	public MapSales getMapSales(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(MapSales.class).readValue(s);
	}
	
	public List<MapSales> getMapSalesList(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<MapSales>>(){});
	}
	
	public MessageMini getMessageMini(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(MessageMini.class).readValue(s);
	}
	
	public List<MessageMini> getMessageMiniList(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<MessageMini>>(){});
	}
	
	public Message getMessage(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(Message.class).readValue(s);
	}
	
	public List<Message> getMessageList(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<Message>>(){});
	}


	public UseInfo getUseInfo(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().reader(UseInfo.class).readValue(s);
	}

	public List<UseInfo> getUseInfoList(Object bean) throws IOException
	{
		String s = new ObjectMapper().writer().writeValueAsString(bean);
		return  new ObjectMapper().readValue(s, new TypeReference<List<UseInfo>>(){});
	}


}