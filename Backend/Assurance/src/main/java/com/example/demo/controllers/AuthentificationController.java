package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@SuppressWarnings("deprecation")
@Controller
public class AuthentificationController {
	
	 @Autowired
	 private UserDao userDao;
	 
    @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	  @ResponseBody
	    public String login(@RequestBody JSONObject payload) {
		  System.out.println("******************************************"+payload);
		  JSONParser parser = new JSONParser();
		  JSONObject json = new JSONObject();;
		  User user;
		  String response=null;
try {
			//json = (JSONObject) parser.parse(payload);
		
		 json=payload;
		  String login=(String) json.get("login");
		  System.out.println("***********************login "+login);
		  String password=(String) json.get("password");
		  System.out.println("***********************password "+password);
		  ObjectMapper objMapper = new ObjectMapper();
		
		 user= userDao.signIn(login, password);
		 if (user !=null){
			
			response=objMapper.writeValueAsString(user);
			
		 }
		 else {user= new User();
			 user.setPonum(BigDecimal.ZERO);
			 response=objMapper.writeValueAsString(user);}
	      
}
	catch(Exception e) {
		e.printStackTrace();
		// obj.put("response", "ERROR");
		 //obj.put("msg", e.getMessage());
}
	
	      return response;
	    }

    
    
    @CrossOrigin(origins = "http://localhost:4200")
   	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
   	  @ResponseBody
   	    public String changePassword(@RequestBody JSONObject payload) {
    	 System.out.println("******************************************"+payload);
		  JSONParser parser = new JSONParser();
		  JSONObject json = new JSONObject();;
		  User user = new User();
		  String response=null;
try {
			//json = (JSONObject) parser.parse(payload);
		
		 json=payload;
		  String newPassword=(String) json.get("newPassword");
		  String oldPassword=(String) json.get("oldPassword");
		  String mat=(String) json.get("mat"); 
		  Integer sponum=(Integer) json.get("ponum");
		  BigDecimal ponum=new BigDecimal(sponum);
		
		 int result= userDao.changePassword(oldPassword, newPassword, ponum, mat);
		 if (result >0){
			response="OK";
		 }
		 else {response="ERROR";}

	  		ObjectMapper objMapper = new ObjectMapper();
		  response=objMapper.writeValueAsString(response);
}
	catch(Exception e) {
		e.printStackTrace();
		response="ERROR";		
}
	
	      return response;
    }
   
    @CrossOrigin(origins = "http://localhost:4200")
  	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
  	  @ResponseBody
  	    public String adduser(@RequestBody JSONObject payload) throws JsonProcessingException {
  		  System.out.println("******************************************"+payload);
  		  JSONParser parser = new JSONParser();
  		  JSONObject json = new JSONObject();
  		  User user= new User();
  		 String response=null;
  		ObjectMapper objMapper = new ObjectMapper();
  try {
  			//json = (JSONObject) parser.parse(payload);
  		
  		 json=payload;
  		  String login=(String) json.get("login");
  		  String password=(String) json.get("password");
  		  String mat=(String) json.get("mat");
		  BigDecimal ponum=new BigDecimal( (Integer) json.get("ponum"));
  		  
  		
  		 int resp= userDao.addUser(password, login, ponum, mat);
  		 if (resp ==0){user.setPonum(BigDecimal.ZERO);}

  		 else {user.setPonum(BigDecimal.ONE);}	
  }
 
  	catch(Exception e) {
  		e.printStackTrace();
  }
  
  response=objMapper.writeValueAsString(user);
  	      return response;
  	    }


}