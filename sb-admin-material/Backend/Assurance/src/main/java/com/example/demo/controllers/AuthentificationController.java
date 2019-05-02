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
		  User user = new User();
		  String response=null;
try {
			//json = (JSONObject) parser.parse(payload);
		
		 json=payload;
		  String login=(String) json.get("login");
		  System.out.println("***********************login "+login);
		  String password=(String) json.get("password");
		  System.out.println("***********************password "+password);
		
		 user= userDao.signIn(login, password);
		 if (user !=null){
			ObjectMapper objMapper = new ObjectMapper();
			response=objMapper.writeValueAsString(user);
			
		 }
		 else {user.setPonum(BigDecimal.ZERO);}
	      
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
		  System.out.println("***********************newPassword "+newPassword);
		  String oldPassword=(String) json.get("oldPassword");
		  System.out.println("***********************oldPassword "+oldPassword);
		  BigDecimal userId=(BigDecimal) json.get("userId");
		
		 int result= userDao.changePassword(oldPassword, newPassword, userId);
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


}
