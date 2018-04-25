package com.wipro.controller;


import java.io.IOException;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.model.Employee;

@Controller
public class EmployeeController {
	@RequestMapping(value= "/getEmployees", method = RequestMethod.GET)
	public ModelAndView getEmployeeInfo(){
		return new ModelAndView("getEmployee");
	}
	@RequestMapping(value= "/showEmployess", method = RequestMethod.GET)
	public ModelAndView showEmployeeInfo(@RequestParam("code") String code) throws JsonProcessingException, IOException{
		ResponseEntity<String> respnse =null;
		System.out.print("oauth_code "+code);
		RestTemplate template = new RestTemplate();
		
		String credentials = "clientId:secret";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		HttpHeaders header = new HttpHeaders();
		
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("Authorization", "Basic " + encodedCredentials);
		
		
		HttpEntity<String> request = new HttpEntity<String>(header);
		
		String access_token_url = "http://localhost:8088/oauth/token";
		
		access_token_url += "?code=" +code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:8099/showEmployess";
		
		respnse = template.exchange(access_token_url, HttpMethod.POST,request, String.class);
		
		System.out.println("access token "+respnse.getBody());
		
		//getting access token from json response
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(respnse.getBody());
		
		String token = node.path("access_token").asText();
		
		String url = "http://localhost:8088/user/getEmployeeList";
		
		System.out.println(token +" token");
		HttpHeaders headers1= new HttpHeaders();
		headers1.add("Authorization","Bearer "+ token);
		
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		
		ResponseEntity<Employee[]> employees = template.exchange(url, HttpMethod.GET,entity,Employee[].class);
		
		Employee[] employeeArray = employees.getBody();
		
		ModelAndView view = new ModelAndView("showEmployess");
		view.addObject("employees", Arrays.asList(employeeArray));
		return view;
		
		
	}
	
}
