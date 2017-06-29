package es.smt.appfrigo.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.smt.appfrigo.bean.SupportBean;


@Controller
@RequestMapping("/support")
public class SupportController implements Serializable{


	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(SupportBean support) {	

		try {
		//	MyEmailer.getInstance().SendMail(support);
		} catch (Exception e) {
			e.printStackTrace();
		}

		 return "redirect:/";

	 }
	
}