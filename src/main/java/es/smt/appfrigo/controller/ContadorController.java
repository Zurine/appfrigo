package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Contador;
import es.smt.appfrigo.bean.ContadorSearch;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ContadorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.service.IContadorService;

@Controller
@RequestMapping("/contador")
public class ContadorController {
	
	private String folder = "contador";
	
	@Autowired
	private IContadorService contadorService;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ModelAndView list(Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			model.addAttribute("contadorSearch", new ContadorSearch());
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
		
		
		return new ModelAndView(folder+"/data");
	 }
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@Valid ContadorSearch search, BindingResult bindingResult,ModelMap model) {	
		
		List<Contador> result = new ArrayList<Contador>();
		Contador total = new Contador();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = contadorService.getContadorData(2,search.getStartDate(), search.getEndDate(), user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getContador(r.getResponse());
					total = ContadorManager.getInstance().getTotalContador(result);
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return "redirect:/login";
		}
		
		model.addAttribute("total",total);
		model.addAttribute("contadorList",result);
		
		return folder+"/data :: resultsList";
	 }

}
