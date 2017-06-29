package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.User;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.UserConverter;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.UserDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IUserAppService;

@Controller
@RequestMapping("/userapp")
public class UserAppController {


	@Autowired
	private IUserAppService userappService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {	
		
		System.out.println("list");
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = userappService.getUserList(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<UserDTO> list = ParseJSON.getInstance().getUserList(r.getResponse());
					List<User> result = UserConverter.getInstance().dtoToBeanList(list);
					
					model.addAttribute("userList", result);
					System.out.println("Length-> " + list.size());
				}
				
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else
		{
			//Mostrar error no session
		}

		
		
		return "userapp/list";

	 }
}
