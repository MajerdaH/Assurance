package com.example.demo.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.RefundDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Controller
public class RefundController {
	
	
	 @Autowired
	 private RefundDao refundDao;


	   @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/getMemberRefundsBy/{mat}/{ponum}/{wbull}", method = RequestMethod.GET)
	  @ResponseBody
	  public String index(@PathVariable String mat,@PathVariable BigDecimal ponum, @PathVariable BigDecimal wbull) {
		 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		 String json =null;
		 System.out.println("ponum==="+ponum);
		 try {
			 json=	ow.writeValueAsString(refundDao.getRefundsByMatAndPonum(ponum, mat, wbull));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return json;
	  }
	   
	   
	   @CrossOrigin(origins = "http://localhost:4200")
		  @RequestMapping(value = "/getMemberBullsBy/{mat}/{ponum}", method = RequestMethod.GET)
		  @ResponseBody
		  public String getMemberBullsBy(@PathVariable String mat,@PathVariable BigDecimal ponum) {
			 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			 String json =null;
			 try {
				 json=	ow.writeValueAsString(refundDao.getBullByMatAndPonum(ponum, mat));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return json;
		  }
	 

}
