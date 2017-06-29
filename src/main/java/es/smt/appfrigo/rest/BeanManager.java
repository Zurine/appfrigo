package es.smt.appfrigo.rest;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Beacon;
import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Contador;
import es.smt.appfrigo.bean.Dashboard;
import es.smt.appfrigo.bean.Dashboard2;
import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.Promotion;
import es.smt.appfrigo.bean.Rates;
import es.smt.appfrigo.bean.Retail;
import es.smt.appfrigo.bean.RetailLocation;
import es.smt.appfrigo.bean.SalesStat;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.bean.UserMini;
import es.smt.appfrigo.bean.Voucher;
import es.smt.appfrigo.converter.BeaconConverter;
import es.smt.appfrigo.converter.CategoryConverter;
import es.smt.appfrigo.converter.ContadorConverter;
import es.smt.appfrigo.converter.ProductConverter;
import es.smt.appfrigo.converter.PromotionConverter;
import es.smt.appfrigo.converter.RetailConverter;
import es.smt.appfrigo.converter.SalesConverter;
import es.smt.appfrigo.converter.UserConverter;
import es.smt.appfrigo.converter.VoucherConverter;
import es.smt.appfrigo.model.BeaconDTO;
import es.smt.appfrigo.model.ContadorDTO;
import es.smt.appfrigo.model.DataDTO;
import es.smt.appfrigo.model.Home2DTO;
import es.smt.appfrigo.model.HomeDTO;
import es.smt.appfrigo.model.ListDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;
import es.smt.appfrigo.model.StatisticsDTO;
import es.smt.appfrigo.model.UserDTO;
import es.smt.appfrigo.model.VoucherDTO;

public class BeanManager {

private static BeanManager instance;
	
	public BeanManager() 
	{
	     super();
	}
	
	public static BeanManager getInstance()
	{
		if (instance == null )
		{
			instance = new BeanManager();
		}
		return instance;
	}
	
	public SelectProduct getSelectProduct(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		ProductDTO dto = ParseJSON.getInstance().getProductDTO(response);
		SelectProduct result = ProductConverter.getInstance().dtoToSelectProductBean(dto);
		
		return result;
	}
	
	public Beacon getBeacon(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		BeaconDTO dto = ParseJSON.getInstance().getBeaconDTO(response);
		Beacon result = BeaconConverter.getInstance().dtoToBean(dto);
		
		return result;
	}
	
	public List<Beacon> getBeaconList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<BeaconDTO> list = ParseJSON.getInstance().getBeaconDTOList(response);
		List<Beacon> result = BeaconConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
	
//	public Notification getNotification(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		NotificationDTO dto = ParseJSON.getInstance().getNotificationDTO(response);
//		Notification result = NotificationConverter.getInstance().dtoToBean(dto);
//		
//		return result;
//	}
//	
//	public List<Notification> getNotificationList(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<NotificationDTO> list = ParseJSON.getInstance().getNotificationDTOList(response);
//		List<Notification> result = NotificationConverter.getInstance().dtoToBeanList(list);
//		
//		return result;
//	}
	
	public Promotion getPromotion(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		OfferDTO list = ParseJSON.getInstance().getOfferDTO(response);
		Promotion result = PromotionConverter.getInstance().dtoToBean(list);
		
		return result;
	}
	
	public List<Promotion> getPromotionList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<OfferDTO> list = ParseJSON.getInstance().getOfferDTOList(response);
		List<Promotion> result = PromotionConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
//	
//	public PromotionBeacon getPromotionDetail(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		OfferDetailsDTO list = ParseJSON.getInstance().getOfferDetailsDTO(response);
//		PromotionBeacon result = PromotionConverter.getInstance().dtoDetailsToBean(list);
//		
//		return result;
//	}
	
//	public List<PromotionBeacon> getPromotionListDetail(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<OfferDetailsDTO> list = ParseJSON.getInstance().getOfferDetailsDTOList(response);
//		List<PromotionBeacon> result = PromotionConverter.getInstance().dtoDetailsToBeanList(list);
//		
//		return result;
//	}

//	public UserMini getUserMini(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		UserDTO dto = ParseJSON.getInstance().getUser(response);
//		UserMini result = UserConverter.getInstance().dtoToMiniBean(dto);
//		
//		return result;
//	}

	public List<UserMini> getUserMiniList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<UserDTO> list = ParseJSON.getInstance().getUserList(response);
		List<UserMini> result = UserConverter.getInstance().dtoToMiniBeanList(list);
		
		return result;
	}
	
//	public User getUser(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		UserDTO dto = ParseJSON.getInstance().getUser(response);
//		User result = UserConverter.getInstance().dtoToBean(dto);
//		
//		return result;
//	}

//	public List<User> getUserList(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<UserDTO> list = ParseJSON.getInstance().getUserList(response);
//		List<User> result = UserConverter.getInstance().dtoToBeanList(list);
//		
//		return result;
//	}
	
//	public UserAdminDTO getUserAdmin(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		UserAdminDTO result = ParseJSON.getInstance().getUserAdminDTO(response);
////		UserAdmin result = UserConverter.getInstance().dtoAdminToBean(dto);
//		
//		return result;
//	}

//	public List<UserAdminDTO> getUserAdminList(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<UserAdminDTO> result = ParseJSON.getInstance().getUserAdminDTOList(response);
////		List<UserAdmin> result = UserConverter.getInstance().dtoAdminToBeanList(list);
//		
//		return result;
//	}
//	public List<Traffic> getTraffic(Object response, int type) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<StatisticsDTO> list = ParseJSON.getInstance().getStatisticsDTOList(response);
//		List<Traffic> result = SalesConverter.getInstance().dtoToTrafficBeanList(list,type);
//		
//		return result;
//	}
//	
//	
	public List<SalesStat> getSales(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<StatisticsDTO> list = ParseJSON.getInstance().getStatisticsDTOList(response);
		List<SalesStat> result = SalesConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
	
	
	public List<Contador> getContador(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<ContadorDTO> list = ParseJSON.getInstance().getContadorDTOList(response);
		List<Contador> result = ContadorConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}

	
	public Dashboard getDashboard(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		HomeDTO dto = ParseJSON.getInstance().getHomeDTO(response);
		Dashboard result = new Dashboard();
		
		result.setEquipmentNumber(dto.getEquipmentNumber());
		result.setTotalEquipmentNumber(dto.getTotalEquipmentNumber());
		result.setSellerNumber(dto.getSellerNumber());
		result.setTotalSellerNumber(dto.getTotalSellerNumber());
		
		result.setSalesNumber(dto.getSalesNumber());
		result.setTotalSalesNumber(dto.getTotalSalesNumber());
		
		result.setUnitNumber(dto.getUnitNumber());
		result.setTotalUnitNumber(dto.getTotalUnitNumber());
			
		return result;
	}
	
	public Rates getRates(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		HomeDTO dto = ParseJSON.getInstance().getHomeDTO(response);
		Rates result = new Rates();
		result.setDays(dto.getDays());
		result.setUnits(dto.getUnits());
		result.setSalesDay(dto.getSalesDay());
		result.setSalesEquiment(dto.getSalesEquiment());
			
		return result;
	}
	
	
	public Dashboard2 getDashboard2(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		Home2DTO dto = ParseJSON.getInstance().getHome2DTO(response);
		Dashboard2 result = new Dashboard2();
		
		result.setExchangedVouchers(dto.getExchangedVouchers());
		result.setExchangedPromos(dto.getExchangedPromos());
		result.setAssessmentsTotal(dto.getAssessmentsTotal());
		result.setMovement(dto.getMovement());
		result.setTotalUsers(dto.getTotalUsers());
		result.setVoucherById(new ObjectMapper().writer().writeValueAsString(dto.getVoucherById()));
		result.setVoucherByRetail(new ObjectMapper().writer().writeValueAsString(dto.getVoucherByRetail()));
		result.setVoucherByRange(new ObjectMapper().writer().writeValueAsString(dto.getVoucherByRange()));
		double[] resultValue = new double[1];
		resultValue[0]  =  ((double)dto.getActiveUser() / dto.getTotalUsers())*100;
		result.setActiveUser((int)resultValue[0]);
		resultValue[0]  =  ((double)dto.getNewUser() / dto.getTotalUsers())*100;
		result.setNewUser((int)resultValue[0]);
		int total = 0;		
		if(dto.getUserStudies()!=null && dto.getUserStudies().size()>0)
		{
			for(DataDTO d: dto.getUserStudies())
			{
				total += d.getData();
			}
		}
		result.setUserGender(dto.getUserGender());
		result.setUserStudies(dto.getUserStudies());
		result.setNewUsers(total);
		resultValue[0]  =  ((double)dto.getUserBuyer() / total)*100;
		result.setUserBuyer((int)resultValue[0]); 
		
		result.setPromotionById(dto.getPromotionById());
		result.setPromotionByType(dto.getPromotionByType());
		
		result.setAnswer1(dto.getAnswer1());
		result.setAnswer2(dto.getAnswer2());
		
		total = 0;		
		if(dto.getAnswer1()!=null && dto.getAnswer1().size()>0)
		{
			for(DataDTO d: dto.getAnswer1())
			{
				total += d.getData();
			}
		}
		resultValue[0]  =  ((double)total / dto.getExchangedVouchers())*100;
		result.setAnswers((int)resultValue[0]); 
		
		
		result.setVoucherByGender(new ObjectMapper().writer().writeValueAsString(dto.getVoucherByGender()));
		result.setVoucherByStudy(new ObjectMapper().writer().writeValueAsString(dto.getVoucherByStudy()));
		result.setVoucherByBuyer(new ObjectMapper().writer().writeValueAsString(dto.getVoucherByBuyer()));
		
		result.setPromotionByGender(new ObjectMapper().writer().writeValueAsString(dto.getPromotionByGender()));
		result.setPromotionByStudy(new ObjectMapper().writer().writeValueAsString(dto.getPromotionByStudy()));
		result.setPromotionByBuyer(new ObjectMapper().writer().writeValueAsString(dto.getPromotionByBuyer()));
			
		return result;
	}
//
	public Category getCategory(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		Category dto = ParseJSON.getInstance().getCategoryDTO(response);
		Category result = CategoryConverter.getInstance().dtoToBean(dto);
		
		return result;
	}
//	
	public List<Category> getCategoryList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<Category> list = ParseJSON.getInstance().getCategoryDTOList(response);
		List<Category> result = CategoryConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
	
	public Retail getRetail(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		RetailDTO dto = ParseJSON.getInstance().getRetailDTO(response);
		Retail result = RetailConverter.getInstance().dtoToBean(dto);
		
		return result;
	}
	
	public List<Retail> getRetailList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<RetailDTO> list = ParseJSON.getInstance().getRetailDTOList(response);
		List<Retail> result = RetailConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
	
	public RetailLocation getRetailLocation(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		RetailLocationDTO dto = ParseJSON.getInstance().getRetailLocatioDTO(response);
		RetailLocation result = RetailConverter.getInstance().dtoToBean(dto);
		
		return result;
	}
	
	public Voucher getVoucher(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		VoucherDTO dto = ParseJSON.getInstance().getVoucherDTO(response);
		Voucher result = VoucherConverter.getInstance().dtoToBean(dto);
		
		return result;
	}
	
	public List<Voucher> getVoucherList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<VoucherDTO> list = ParseJSON.getInstance().getVoucherDTOList(response);
		List<Voucher> result = VoucherConverter.getInstance().dtoToBeanList(list);
		
		return result;
	}
	
	public Data getData(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		ListDTO dto = ParseJSON.getInstance().getListDTO(response);
		Data result = VoucherConverter.getInstance().dtoToBeanData(dto);
		
		return result;
	}
	
	public List<Data> getDataList(Object response) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<ListDTO> list = ParseJSON.getInstance().getListDTOList(response);
		List<Data> result = VoucherConverter.getInstance().dtoToBeanListData(list);
		
		return result;
	}
	
//	public Operator getOperator(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		OperatorDTO dto = ParseJSON.getInstance().getOperatorDTO(response);
//		Operator result = OperatorConverter.getInstance().dtoToBean(dto);
//		
//		return result;
//	}
//	
//	public List<Operator> getOperatorList(Object response) throws JsonGenerationException, JsonMappingException, IOException
//	{
//		List<OperatorDTO> list = ParseJSON.getInstance().getOperatorDTOList(response);
//		List<Operator> result = OperatorConverter.getInstance().dtoToBeanList(list);
//		
//		return result;
//	}
}
