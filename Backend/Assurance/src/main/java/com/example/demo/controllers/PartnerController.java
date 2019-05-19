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

import com.example.demo.dao.PartnerDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class PartnerController {
	
	@Autowired
	 private PartnerDao partnerDao;

	   @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/getPartnersBy/{mat}/{ponum}", method = RequestMethod.GET)
	  @ResponseBody
	  public String index(@PathVariable String mat,@PathVariable BigDecimal ponum) {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json =null;
		 System.out.println("ponum==="+ponum);
		 try {
			 json=	ow.writeValueAsString(partnerDao.getPartnersByMember(mat, ponum));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return json;
	  }
	   
	   
	   @CrossOrigin(origins = "http://localhost:4200")
	   	@RequestMapping(value = "/addPartner", method = RequestMethod.POST)
	   	  @ResponseBody
	   	    public String addPartner(@RequestBody JSONObject payload) throws JsonProcessingException {
	    //	 System.out.println("******************************************"+payload);
			  JSONParser parser = new JSONParser();
			  JSONObject json = new JSONObject();;
			  User user = new User();
			  String response=null;
			  ObjectMapper objMapper = new ObjectMapper();
	try {
				//json = (JSONObject) parser.parse(payload);
			
			 json=payload;
			  String stype=(String) json.get("type");
			  String date=(String) json.get("date");
			  String name=(String) json.get("name");
			  String mat=(String) json.get("mat");
			  String dateN=(String) json.get("date");
			  String sponum=(String) json.get("ponum");
			  BigDecimal ponum=new BigDecimal(sponum);
			  BigDecimal type=new BigDecimal(stype);
			  
			System.out.println(dateN);
			String hdateN=dateN.substring(0,dateN.indexOf("T"));
			System.out.println(hdateN.replace("-", ""));
		//	dateN=dateN.substring(hdateN.indexOf("T"));
			 int result= partnerDao.insertPartner(type, ponum, mat, hdateN.replace("-", ""), name, ponum+mat, ponum+mat+type,"0");
					// changeMemberInfos(ponum, mat, address, phone, rib);
			 if (result >0){
				response="OK";
			 }
			 else {response="ERROR";}
	}
		catch(Exception e) {
			e.printStackTrace();
			response="ERROR";		
	}
		System.out.println(response);
		      return objMapper.writeValueAsString(response).toString();
	    }


}
