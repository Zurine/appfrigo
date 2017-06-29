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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IChannelService;

@Controller
@RequestMapping("/channel")
public class ChannelController {

	private String folder = "channel";
	
	@Autowired
	private IChannelService channelService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Channel> result = new ArrayList<Channel>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = channelService.list(user.getToken());

				if(r.getError().getCode() == Constants.codeOK)
				{
					result =  ParseJSON.getInstance().getChannelDTOList(r.getResponse());
					
					System.out.println("Length-> " + result.size());
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
		else return new ModelAndView(Constants.logoutRedirect);

		model.addAttribute("channelList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		Channel c = new Channel();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			model.addAttribute("channel", c);
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("title",	setTitle(c));

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Channel channel, BindingResult bindingResult,ModelMap model, RedirectAttributes redirect) {	
		
		if (bindingResult.hasErrors()) 
		{
			model.addAttribute(Constants.errormsg,
				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
		}
		else
		{
			UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user!=null && user.isCredentialsNonExpired())
			{
				try {
					ResponseDTO r  = null;
					if(channel.getId() == 0){
						r = channelService.add(channel, user.getToken());
					}else{
						r = channelService.edit(channel, user.getToken());
					}
					if(r.getError().getCode() == Constants.codeOK)
					{
						redirect.addFlashAttribute(Constants.infomsg, "Successfully saved");
						if(channel.isNext())
							return new ModelAndView("redirect:/"+folder+"/add");
						else return new ModelAndView("redirect:/"+folder+"/list");
					}
					else model.addAttribute(Constants.errormsg, r.getError().getDesc());
					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else return new ModelAndView(Constants.logoutRedirect);
		}
		model.addAttribute("title",	setTitle(channel));
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model) {	
		
		Channel channel = new Channel();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = channelService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					channel = ParseJSON.getInstance().getChannelDTO(r.getResponse());
					model.addAttribute("channel", channel);
					model.addAttribute("title",	setTitle(channel));
					
					return new ModelAndView(folder+"/add");
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
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = channelService.activate(state, id, user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = channelService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
				}
				else
				{
					boolean res = ParseJSON.getInstance().getBoolean(r.getResponse());
					if(!res)
						redirect.addFlashAttribute(Constants.errormsg,"You cannot delete this item");
					else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/list");
	}
	
	private String setTitle(Channel c){
		String title = "";
		if(c.getId() == 0)
			title = "Create New Channel";
		else title = "Edit " + c.getName();
		
		return title;
	}
	
}
