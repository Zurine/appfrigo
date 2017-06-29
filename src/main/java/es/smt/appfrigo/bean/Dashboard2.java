package es.smt.appfrigo.bean;

import java.util.List;

import es.smt.appfrigo.model.DataDTO;

public class Dashboard2 {

	private int exchangedVouchers;
    private int totalUsers;
    private int exchangedPromos;
    private int assessmentsTotal;
    private int movement;
    
    private String voucherById;
    private String voucherByRetail;
    private String voucherByRange;
    
    private int activeUser;
    private List<DataDTO> userGender;
    private List<DataDTO> userStudies;
    private int userBuyer;
    private int newUsers;
    
    private List<DataDTO> promotionById;
    private List<DataDTO> promotionByType;
    
    private List<DataDTO> answer1;
    private List<DataDTO> answer2;
    private int answers;
    
    private String voucherByGender;
    private String voucherByStudy;
    private String voucherByBuyer;
    
    private String promotionByGender;
    private String promotionByStudy;
    private String promotionByBuyer;
    
    private int newUser;
    
	public Dashboard2() {
		super();
	}

	public Dashboard2(int exchangedVouchers, int totalUsers, int exchangedPromos, int assessmentsTotal, int movement) {
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
	

	public String getVoucherById() {
		return voucherById;
	}

	public void setVoucherById(String voucherById) {
		this.voucherById = voucherById;
	}
	
	public String getVoucherByRetail() {
		return voucherByRetail;
	}

	public void setVoucherByRetail(String voucherByRetail) {
		this.voucherByRetail = voucherByRetail;
	}
	
	public String getVoucherByRange() {
		return voucherByRange;
	}

	public void setVoucherByRange(String voucherByRange) {
		this.voucherByRange = voucherByRange;
	}
	
	public int getNewUser() {
		return newUser;
	}

	public void setNewUser(int newUser) {
		this.newUser = newUser;
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
	
	
	public int getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(int newUsers) {
		this.newUsers = newUsers;
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

	public int getAnswers() {
		return answers;
	}

	public void setAnswers(int answers) {
		this.answers = answers;
	}

	public String getVoucherByGender() {
		return voucherByGender;
	}

	public void setVoucherByGender(String voucherByGender) {
		this.voucherByGender = voucherByGender;
	}

	public String getVoucherByStudy() {
		return voucherByStudy;
	}

	public void setVoucherByStudy(String voucherByStudy) {
		this.voucherByStudy = voucherByStudy;
	}

	public String getVoucherByBuyer() {
		return voucherByBuyer;
	}

	public void setVoucherByBuyer(String voucherByBuyer) {
		this.voucherByBuyer = voucherByBuyer;
	}

	public String getPromotionByGender() {
		return promotionByGender;
	}

	public void setPromotionByGender(String promotionByGender) {
		this.promotionByGender = promotionByGender;
	}

	public String getPromotionByStudy() {
		return promotionByStudy;
	}

	public void setPromotionByStudy(String promotionByStudy) {
		this.promotionByStudy = promotionByStudy;
	}

	public String getPromotionByBuyer() {
		return promotionByBuyer;
	}

	public void setPromotionByBuyer(String promotionByBuyer) {
		this.promotionByBuyer = promotionByBuyer;
	}

	@Override
	public String toString() {
		return "Dashboard2 [exchangedVouchers=" + exchangedVouchers + ", totalUsers=" + totalUsers
				+ ", exchangedPromos=" + exchangedPromos + ", assessmentsTotal=" + assessmentsTotal + ", movement="
				+ movement + "]";
	}
    
    
	    
}

