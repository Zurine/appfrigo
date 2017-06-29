package es.smt.appfrigo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler{
 
 String path = "/error";
  

 @RequestMapping(value="/400")
 public String error400(final HttpServletResponse response){
  System.out.println("custom error handler");
  return path+"/400";
 }
  
 @RequestMapping(value="/404")
 public String error404(final HttpServletResponse response){
  System.out.println("custom error handler");
  response.setHeader("Cache-Control", "no-cache");
  return path+"/404";
 }
  
 @RequestMapping(value="/500")
 public String error500(){
  System.out.println("custom error handler");
  return path+"/500";
 }
  
 @RequestMapping(value="/403")
 public String error403(){
  System.out.println("custom error handler");
  return path+"/403";
 }
  
}
