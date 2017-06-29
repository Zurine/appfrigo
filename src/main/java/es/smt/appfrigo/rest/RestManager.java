package es.smt.appfrigo.rest;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.constants.PropertiesManager;
import es.smt.appfrigo.model.ResponseDTO;


public class RestManager {
	
	private static RestManager instance;
	
	private Logger logger = Logger.getLogger(RestManager.class);
	
	public RestManager(){}
	
	public static RestManager getInstance()
	{
		if (instance == null )
		{
			instance = new RestManager();
		}
		return instance;
	}

	public ResponseDTO exchangePostWithString(String request, String url) throws JsonProcessingException, IOException
	{
		ResponseDTO response = new ResponseDTO();

		try{
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/json");
		    headers.add("Content-Type", "application/json");
		    
		    logger.info("URL: " + url);
		    logger.info("REQUEST: " + request);
	    
			HttpEntity<String> requestEntity = new HttpEntity<String>(request,headers);
			
			CloseableHttpClient httpClient = HttpClients.custom()
					                 .setSSLHostnameVerifier(new NoopHostnameVerifier())
					                 .build();
		    HttpComponentsClientHttpRequestFactory requestFactory = 
					      new HttpComponentsClientHttpRequestFactory();
		    requestFactory.setHttpClient(httpClient);
		    
			RestTemplate restTemplate = new RestTemplate(requestFactory);	
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			
			//String url3 = "http://localhost:1227";//"http://smtdev-appfrigoback.azurewebsites.net/"; 
			ResponseEntity<String> restResponse = restTemplate.exchange(PropertiesManager.getInstance().getProperty("server.url")+url,HttpMethod.POST,requestEntity,String.class);
			if(restResponse!=null){
				response = new ObjectMapper().reader(ResponseDTO.class).readValue(restResponse.getBody());
			}
			System.out.println("RESPONSE -- > " + response.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("e--> " + e.getMessage().toString());
			System.out.println("e--> " + e.toString());
		} 
		
		return response;
		
	}

	public HttpEntity<String> getPostWithString(String data, String url){
		RestTemplate restTemplate = new RestTemplate();	
		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(data,requestHeaders);
		HttpEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);
		if(response!=null && response.getBody()!=null && !response.getBody().equals(""))
			return response;
		else return null;
	}
	
		public HttpEntity<String> getRequest( String url){
		RestTemplate restTemplate = new RestTemplate();	
//		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,null,String.class);
		if(response!=null && response.getBody()!=null && !response.getBody().equals(""))
			return response;
		else return null;
	}
}
