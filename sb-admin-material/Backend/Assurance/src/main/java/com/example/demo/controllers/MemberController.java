package com.example.demo.controllers;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.MemberDaoImpl;
import com.example.demo.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.math.BigDecimal;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Controller
public class MemberController {
	
	
	  @Autowired
	 private MemberDao memberDao;


	   @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/getMemberBy/{mat}/{ponum}", method = RequestMethod.GET)
	  @ResponseBody
	  public String index(@PathVariable String mat,@PathVariable BigDecimal ponum) {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json =null;
		 System.out.println("ponum==="+ponum);
		 try {
			 json=	ow.writeValueAsString(memberDao.findById(ponum, mat));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return json;
	  }
	   
	   @CrossOrigin(origins = "http://localhost:4200")
	   	@RequestMapping(value = "/changeMemberInfos", method = RequestMethod.POST)
	   	  @ResponseBody
	   	    public String changeMemberInfos(@RequestBody JSONObject payload) {
	    //	 System.out.println("******************************************"+payload);
			  JSONParser parser = new JSONParser();
			  JSONObject json = new JSONObject();;
			  User user = new User();
			  String response=null;
	try {
				//json = (JSONObject) parser.parse(payload);
			
			 json=payload;
			  String address=(String) json.get("address");
			  System.out.println("address "+address);
			  String phone=(String) json.get("phone");
			  System.out.println("phone "+phone);
			  String rib=(String) json.get("phone");
			  String mat=(String) json.get("mat")
					  ;
			  System.out.println("rib "+rib);
			  BigDecimal ponum=(BigDecimal) json.get("ponum");
			  
			
			 int result= memberDao.changeMemberInfos(ponum, mat, address, phone, rib);
			 if (result ==0){
				response="OK";
			 }
			 else {response="ERROR";}
		      
	}
		catch(Exception e) {
			e.printStackTrace();
			response="ERROR";		
	}
		
		      return response;
	    }


	 
	 
	/* 
	 @RequestMapping("/getMembers")
	  @ResponseBody
	  public String getMembers() throws JsonProcessingException {
		// ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 //String json = ow.writeValueAsString(memberdao.getMembersList());
		  
	    return memberDao.getMembersList().toString();
	  }
*/

}
