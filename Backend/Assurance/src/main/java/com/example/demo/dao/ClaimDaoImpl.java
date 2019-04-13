package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Claim;

public class ClaimDaoImpl implements ClaimDao {
	
	
	public List<Claim> getClaimsByClientMat(String matricule){
		List<Claim> clientClaims = new ArrayList<Claim>();
		
		return clientClaims;
	}
	
	
	public int addClaim(Claim claim) {
		
		int i=1;
		return i;
	}
	
	public int modifyClaim(String response, String status) {
		
		int i=1;
		return i;
	}

}
