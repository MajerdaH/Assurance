package com.example.demo.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.MemberDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class MemberController {
	
	
	  @Autowired
	 private MemberDao memberDao;



	@RequestMapping("/getMemberById")
	  @ResponseBody
	  public String index() throws JsonProcessingException {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json = ow.writeValueAsString(this.memberDao.findById());
		 //MemberDaoImpl dao = new MemberDaoImpl();
		 //dao.findById((long)111);
//		this.memberDao.findById();
		  
	    return json;
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
