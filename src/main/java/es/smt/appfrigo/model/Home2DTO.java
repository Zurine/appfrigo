package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Home2DTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int exchangedVouchers;
    private int totalUsers;
    private int exchangedPromos;
    private int assessmentsTotal;
    private int movement;
    
    private List<DataDTO>voucherById;
    private List<DataDTO>voucherByRetail;
    private List<DataDTO>voucherByRange;
    

    private int activeUser;
    private int newUser;
    private List<DataDTO> userGender;
    private List<DataDTO> userStudies;
    private int userBuyer;
    
    private List<DataDTO> promotionById;
    private List<DataDTO> promotionByType;
    
    private List<DataDTO> answer1;
    private List<DataDTO> answer2;
    
    private List<DataDTO> voucherByGender;
    private List<DataDTO> voucherByStudy;
    private List<DataDTO> voucherByBuyer;
    

    private List<DataDTO> promotionByGender;
    private List<DataDTO> promotionByStudy;
    private List<DataDTO> promotionByBuyer;
    
	public Home2DTO() {
		super();
	}

	public Home2DTO(int exchangedVouchers, int totalUsers, int exchangedPromos, int assessmentsTotal, int movement) {
		super();
		this.exchangedVouchers = exchangedVouchers;
		this.totalUsers = totalUsers;
		this.exchangedPromos = exchangedPromos;
		this.assessmentsTotal = assessmentsTotal;
		this.movement = movement;
	}

	public int getExchangedVouchers() {
		return exchangedVouchers;
	}

	public void setExchangedVouchers(int exchangedVouchers) {
		this.exchangedVouchers = exchangedVouchers;
	}

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getExchangedPromos() {
		return exchangedPromos;
	}

	public void setExchangedPromos(int exchangedPromos) {
		this.exchangedPromos = exchangedPromos;
	}

	public int getAssessmentsTotal() {
		return assessmentsTotal;
	}

	public void setAssessmentsTotal(int assessmentsTotal) {
		this.assessmentsTotal = assessmentsTotal;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public List<DataDTO> getVoucherById() {
		return voucherById;
	}

	public void setVoucherById(List<DataDTO> voucherById) {
		this.voucherById = voucherById;
	}

	public List<DataDTO> getVoucherByRetail() {
		return voucherByRetail;
	}

	public void setVoucherByRetail(List<DataDTO> voucherByRetail) {
		this.voucherByRetail = voucherByRetail;
	}
	
	public List<DataDTO> getVoucherByRange() {
		return voucherByRange;
	}

	public void setVoucherByRange(List<DataDTO> voucherByRange) {
		this.voucherByRange = voucherByRange;
	}

	public int getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(int activeUser) {
		this.activeUser = activeUser;
	}

	public List<DataDTO> getUserGender() {
		return userGender;
	}

	public void setUserGender(List<DataDTO> userGender) {
		this.userGender = userGender;
	}

	public List<DataDTO> getUserStudies() {
		return userStudies;
	}

	public void setUserStudies(List<DataDTO> userStudies) {
		this.userStudies = userStudies;
	}

	public int getUserBuyer() {
		return userBuyer;
	}

	public void setUserBuyer(int userBuyer) {
		this.userBuyer = userBuyer;
	}
	
	public List<DataDTO> getPromotionById() {
		return promotionById;
	}

	public void setPromotionById(List<DataDTO> promotionById) {
		this.promotionById = promotionById;
	}

	public List<DataDTO> getPromotionByType() {
		return promotionByType;
	}

	public void setPromotionByType(List<DataDTO> promotionByType) {
		this.promotionByType = promotionByType;
	}

	public int getNewUser() {
		return newUser;
	}

	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}

	public List<DataDTO> getAnswer1() {
		return answer1;
	}

	public void setAnswer1(List<DataDTO> answer1) {
		this.answer1 = answer1;
	}

	public List<DataDTO> getAnswer2() {
		return answer2;
	}

	public void setAnswer2(List<DataDTO> answer2) {
		this.answer2 = answer2;
	}
	public List<DataDTO> getVoucherByGender() {
		return voucherByGender;
	}

	public void setVoucherByGender(List<DataDTO> voucherByGender) {
		this.voucherByGender = voucherByGender;
	}

	public List<DataDTO> getVoucherByStudy() {
		return voucherByStudy;
	}

	public void setVoucherByStudy(List<DataDTO> voucherByStudy) {
		this.voucherByStudy = voucherByStudy;
	}

	public List<DataDTO> getVoucherByBuyer() {
		return voucherByBuyer;
	}

	public void setVoucherByBuyer(List<DataDTO> voucherByBuyer) {
		this.voucherByBuyer = voucherByBuyer;
	}
	
	public List<DataDTO> getPromotionByGender() {
		return promotionByGender;
	}

	public void setPromotionByGender(List<DataDTO> promotionByGender) {
		this.promotionByGender = promotionByGender;
	}

	public List<DataDTO> getPromotionByStudy() {
		return promotionByStudy;
	}

	public void setPromotionByStudy(List<DataDTO> promotionByStudy) {
		this.promotionByStudy = promotionByStudy;
	}

	public List<DataDTO> getPromotionByBuyer() {
		return promotionByBuyer;
	}

	public void setPromotionByBuyer(List<DataDTO> promotionByBuyer) {
		this.promotionByBuyer = promotionByBuyer;
	}

	@Override
	public String toString() {
		return "Home2DTO [exchangedVouchers=" + exchangedVouchers + ", totalUsers=" + totalUsers + ", exchangedPromos="
				+ exchangedPromos + ", assessmentsTotal=" + assessmentsTotal + ", movement=" + movement
				+ ", voucherById=" + voucherById + ", voucherByRetail=" + voucherByRetail + "]";
	}
}
