package com.example.demo.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class MemberController {
	
	
	
	 private MemberDao memberdao;



	@RequestMapping("/getMemberById")
	  @ResponseBody
	  public String index() throws JsonProcessingException {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json = ow.writeValueAsString(memberdao.findById((long) 111));
		  
	    return json;
	  }
	 
	 
	 
	 @RequestMapping("/getMembers")
	  @ResponseBody
	  public String getMembers() throws JsonProcessingException {
		// ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 //String json = ow.writeValueAsString(memberdao.getMembersList());
		  
	    return memberdao.getMembersList().toString();
	  }


}
