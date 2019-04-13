package com.example.demo.dao;

import java.util.List;
import com.example.demo.entities.Claim;


public interface ClaimDao {
	
	
	public List<Claim> getClaimsByClientMat(String matricule);
		
	public int addClaim(Claim claim);
	
	public int modifyClaim(String response, String status);
}
