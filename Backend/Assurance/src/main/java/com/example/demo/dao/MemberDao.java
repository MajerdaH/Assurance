package com.example.demo.dao;

import java.util.List;

import com.example.demo.entities.Member;


	public interface MemberDao {
	 
	    
	    public Member findById(Long id);

	    
	    public List<Member> getMembersList();
}
