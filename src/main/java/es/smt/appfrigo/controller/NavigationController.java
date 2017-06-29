package es.smt.appfrigo.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class NavigationController implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String printWelcome() {
//		return "index";
//	}
	
	@RequestMapping(value = "/legalAdvise", method = RequestMethod.GET)
	public String legalAdvise() {	
		return "aviso-legal";

	}
	
	@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
	public String privacyPolicy() {	
		return "politica-privacidad";
	}


}