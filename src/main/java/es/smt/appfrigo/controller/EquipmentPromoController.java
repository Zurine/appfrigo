package es.smt.appfrigo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Beacon;
import es.smt.appfrigo.bean.Promotion;
import es.smt.appfrigo.bean.PromotionBeacon;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.EquipmentManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.BeaconDTO;
import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.OfferBeaconDTO;
import es.smt.appfrigo.model.OfferDetailsDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IPromotionService;

@Controller
@RequestMapping("/equipment/promotion")
@SessionAttributes(value =  {"promotionList"})
public class EquipmentPromoController {

	private String folder = "equipment/promotion";
	
	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private HttpSession session;
	
//	private static final Logger logger = Logger.getLogger(EquipmentPromoController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("id") int id,Model model ) {	
		
		List<OfferDetailsDTO> list = new ArrayList<OfferDetailsDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				BusinessDTO business = new BusinessDTO();
				if(session.getAttribute("business") == null)
				{
					ResponseDTO r = businessService.get(id,false, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						business = ParseJSON.getInstance().getBusinessDTO(r.getResponse());
						session.setAttribute("business", business);
					}
				}
				else business = (BusinessDTO)session.getAttribute("business");
					
				if(business!=null)
				{
					for (BeaconDTO b : business.getBeacon()) {
						
						if(b.getObjOffers()!=null && b.getObjOffers().size() > 0)
						{
							for (OfferDetailsDTO p : b.getObjOffers()) {
								list.add(p);
							}
						}
					}
				}
				model.addAttribute("promotionList", list);
				model.addAttribute("businessId", id);
				 
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, "Something was wrong");
		}
		
		return new ModelAndView(folder+"/list");
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("id") int id,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(id!=0)
			{
				try {
					
					ResponseDTO r = businessService.getBeacons(id, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Beacon> result = BeanManager.getInstance().getBeaconList(r.getResponse());
						model.addAttribute("beaconList", result);
					}
					
					
//					Business business = (Business)session.getAttribute("business");
//					List<Beacon> beacons = new ArrayList<Beacon>();
//					if(business!=null)
//					{
//						beacons = business.getBeacon();
//					}
//					model.addAttribute("beaconList", beacons);
					
					r = promotionService.getList(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Promotion> promotions = BeanManager.getInstance().getPromotionList(r.getResponse());
						if(promotions!=null && promotions.size()>0)
						{
							model.addAttribute("promotionList", promotions);
						}
					}
					PromotionBeacon promotion = new PromotionBeacon();
					promotion.setPromotion(new Promotion());
					promotion.getPromotion().setRedimible(false);
					promotion.setBusiness(new BusinessMiniDTO(id));
					model.addAttribute("businessId", id);
					model.addAttribute("promotion", promotion);
					model.addAttribute("timeZone", "");
//					model.addAttribute("redimible", false);
					
					return new ModelAndView(folder+"/add");
				
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	 }

//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public String add(@Valid PromotionBeacon promotion,BindingResult bindingResult,Model model) {	
//		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if(user!=null && user.isCredentialsNonExpired())
//		{	
//			try {
//		
//				if(promotion!=null)
//				{
//					if(promotion.getBusiness()!=null && promotion.getBusiness().getId()!=0)
//					{
//						if(promotion.getPromotion()!=null && promotion.getPromotion().getId()!=0)
//						{
//							ResponseDTO r = businessService.getProducts(promotion.getBusiness().getId(), user.getToken());
//							if(r.getError().getCode() == Constants.codeOK)
//							{
//								List<Product> products = BeanManager.getInstance().getProductList(r.getResponse());
//								r = promotionService.getDetails(promotion.getPromotion().getId(), promotion.getBusiness().getId(), user.getToken());
//								if(r.getError().getCode() == Constants.codeOK)
//								{
//									boolean productValidad = false;
//									boolean totalValidad = true;
//									PromotionBeacon promo = BeanManager.getInstance().getPromotionDetail(r.getResponse());
//									promotion.getPromotion().setRedimible(promo.getPromotion().isRedimible());
//									if(products!=null && products.size()>0 && promo.getPromotion().getProducts()!=null 
//											&& promo.getPromotion().getProducts().size()>0)
//									{
//									
//								/*		for (Product ps : promo.getPromotion().getProducts()) {
//											
//											totalValidad = true;
//											for(Product p: products)
//											{
//												productValidad = false;
//												if(p.getId() == ps.getId())
//												{
//													productValidad = true;
//													break;
//												}
//											}
//											if(!productValidad)
//											{
//												totalValidad = false;
//												break;
//											}
//										}*/
//									}
//								
//									if(!totalValidad)
//									{
//										model.addAttribute("message", "You need more products");
//									}
//									else
//									{
//										model.addAttribute("message", "");
//										model.addAttribute("promotion", promotion);
//										model.addAttribute("timeZone", "");
//									}
//								}
//							}
//						}
//					}
//
//				}
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		else
//		{
//			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
//		}
//
//		 return folder+"/add :: resultsList";
//	 }


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid PromotionBeacon promotion,BindingResult bindingResult,Model model) {	
	System.out.println("addPromotion");
	UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
		
				if(promotion!=null)
				{
					List<OfferBeaconDTO> asignaciones = EquipmentManager.getInstance().associatePromotion(promotion);
					ResponseDTO r = promotionService.asociatePromotion(asignaciones, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						session.setAttribute("business", null);
						return new ModelAndView("redirect:/"+folder+"/list?id="+promotion.getBusiness().getId());
					}
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		 return new ModelAndView("redirect:/"+folder+"/getNew?id="+promotion.getBusiness().getId());
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("offer") int offer,@RequestParam("beacon") int beacon,Boolean state, Model model ) {	
		
		BusinessDTO business = (BusinessDTO)session.getAttribute("business");
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = null;
				if(state)
				{
					r = promotionService.activateAssociation(offer, beacon, user.getToken());
				}
				else r = promotionService.deactivateAssociation(offer, beacon, user.getToken());
				
				if(r.getError().getCode() == Constants.codeOK)
				{
					session.setAttribute("business", null);
					return new ModelAndView("redirect:/"+folder+"/list?id="+business.getId());
				}
				else
				{
					model.addAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("redirect:/"+folder+"/list?id="+business.getId());
	 }
	
	@RequestMapping(value = "/codes", method = RequestMethod.POST)
	public String codes(@Valid PromotionBeacon promotion, BindingResult bindingResult,ModelMap model) {	
		
		boolean code = false;
		if (bindingResult.hasErrors()) 
		{
			model.addAttribute(Constants.errormsg,ErrorManager.getInstance().getBindingResultMessageString(bindingResult.getAllErrors()));
		}
		else
		{
			UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user!=null && user.isCredentialsNonExpired())
			{
				
				if(promotion!=null && promotion.getPromotion()!=null && promotion.getPromotion().getId()!=0)
				{
					@SuppressWarnings("unchecked")
					List<Promotion> promotions = (List<Promotion>)session.getAttribute("promotionList");
					
					if(promotions!=null && promotions.size()>0)
					{
						boolean found = false;
						int cont = 0;
						while(!found && cont < promotions.size())
						{
							if(promotions.get(cont).getId() == promotion.getPromotion().getId())
							{
								code = promotions.get(cont).isRedimible();
								found = true;
							}
							cont++;
						}
					}
				}
			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError);
			}
		}
		promotion.getPromotion().setRedimible(code);

		model.addAttribute("promotion",promotion);
	
		return folder+"/add :: codeDiv";
	 }
	
}
