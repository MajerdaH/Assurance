package com.example.demo.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import com.example.demo.entities.Claim;


public interface ClaimDao {
	
	
	public List<Claim> getClaimsByClientMat(String matricule, BigDecimal ponum) throws ParseException;
		
	public int addClaim(String type, String description, String mat, BigDecimal ponum, String joinedFile);
	
}
