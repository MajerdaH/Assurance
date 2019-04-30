package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entities.Member;


	public interface MemberDao {
	 
	    
	    public Member findById(BigDecimal ponum, String mat);
	    public int changeMemberInfos(BigDecimal ponum,String mat, String address, String phone, String rib);



	    
	//    public List<Member> getMembersList();
}
