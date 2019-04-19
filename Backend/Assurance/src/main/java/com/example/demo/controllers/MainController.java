package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	   @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping("/")
  @ResponseBody
  public String index() {
	  
    return "Hello World!!!";
  }

}
