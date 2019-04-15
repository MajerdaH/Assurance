package com.example.demo.controllers;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


	  @RequestMapping(value = "/getMemberBy/{id}", method = RequestMethod.GET)
	  @ResponseBody
	  public String index(@PathVariable int id) {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json =null;
		 System.out.println("id="+id);
		 try {
			 json=	ow.writeValueAsString(memberDao.findById(BigDecimal.valueOf(id)));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
