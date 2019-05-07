
	package com.example.demo.controllers;

	import java.math.BigDecimal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ClaimDao;
import com.example.demo.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.databind.ObjectWriter;


	@Controller
	public class ClaimController {
		
		
		 @Autowired
		 private ClaimDao claimDao;


		   @CrossOrigin(origins = "http://localhost:4200")
		  @RequestMapping(value = "/getMemberClaimsBy/{mat}/{ponum}", method = RequestMethod.GET)
		  @ResponseBody
		  public String getClaimsByMember(@PathVariable String mat,@PathVariable BigDecimal ponum) {
			 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			 String json =null;
			 System.out.println("ponum==="+ponum);
			 try {
				 json=	ow.writeValueAsString(claimDao.getClaimsByClientMat(mat, ponum));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return json;
		  }
		 

		   
		   @CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value = "/addClaim", method = RequestMethod.POST)
			  @ResponseBody
			    public String login(@RequestBody JSONObject payload) {
				  System.out.println("******************************************"+payload);
				  JSONParser parser = new JSONParser();
				  JSONObject json = new JSONObject();;
				
				  String response=null;
		try {
					//json = (JSONObject) parser.parse(payload);
				
				 json=payload;
				 String sponum=(String) json.get("ponum");
				  BigDecimal ponum=new BigDecimal(sponum);
				  
				  String mat=(String) json.get("mat");
				  String type=(String) json.get("type");
				  String description=(String) json.get("description");
				  String joinedFile=(String) json.get("joinedFile");
				  
				  ObjectMapper objMapper = new ObjectMapper();
				
				  int i=0;
				  i=claimDao.addClaim(type,description,mat,ponum,joinedFile); 
					 response=objMapper.writeValueAsString(i);
			      
		}
			catch(Exception e) {
				e.printStackTrace();
				// obj.put("response", "ERROR");
				 //obj.put("msg", e.getMessage());
		}
			
			      return response;
			    }

	}
