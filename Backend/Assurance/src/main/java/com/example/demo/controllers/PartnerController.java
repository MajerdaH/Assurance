package com.example.demo.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.PartnerDao;
import com.example.demo.dao.UserDao;
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

}
