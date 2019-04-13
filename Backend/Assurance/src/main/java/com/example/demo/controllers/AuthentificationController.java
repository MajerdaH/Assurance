package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@SuppressWarnings("deprecation")
@Controller
public class AuthentificationController {
	
	 @Autowired
	 private UserDao userDao;
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	  @ResponseBody
	    public String login(@RequestBody String payload) {
		  System.out.println("******************************************"+payload);
		  JSONParser parser = new JSONParser();
		  JSONObject json = new JSONObject();;
		  User user = new User();
		  String response=null;
try {
			json = (JSONObject) parser.parse(payload);
		
		 
		  String login=(String) json.get("login");
		  System.out.println("***********************login "+login);
		  String password=(String) json.get("password");
		  System.out.println("***********************password "+password);
		
		 user= userDao.signIn(login, password);
		 if (user !=null){
			ObjectMapper objMapper = new ObjectMapper();
			response=objMapper.writeValueAsString(user);
			
		 }
		 
	      
}
	catch(Exception e) {
		e.printStackTrace();
		// obj.put("response", "ERROR");
		 //obj.put("msg", e.getMessage());
}
	
	      return response;
	    }


}
