package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entities.Member;


	public interface MemberDao {
	 
	    
	    public Member findById(BigDecimal id);

	    
	//    public List<Member> getMembersList();
}
