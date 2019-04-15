package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Member;
@Service
@Transactional 
@Repository("memberDao")
	public class MemberDaoImpl  implements MemberDao {
	 
	    @Autowired
	    private final SessionFactory sessionFactory=null;

	   @Autowired
	    public MemberDaoImpl() {
	    
	    }

	    Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	 
	 
	    public Member findById(BigDecimal id) {
	    	logger.info("id"+id);
	    	 String sql = "select * from adherent where nump="+id;
	    	 Session session;
	    	 try{
		         session = this.sessionFactory.getCurrentSession();

	    	 }catch(Exception e){
	    		 session = this.sessionFactory.openSession();
	    	 }
		        SQLQuery  query = session.createSQLQuery(sql);
		    //    query.setParameter("id", id);
		        Member member =new Member();
		       logger.info("result"+query.getResultList().size());
		        if(query.getResultList().size()>0){
		        for (Object ligneAsObject : query.getResultList()) {

		           Object[] ligne = (Object[]) ligneAsObject ;
		       	
		           member.setNumP((BigDecimal)ligne[0] );
		           member.setMat((String)ligne[1]) ;
		           member.setNom((String)ligne[2]);
		           member.setPrenom((String)ligne[3]);
		           member.setDateN((String)ligne[4]);}
		        }
		        else {member.setNumP(BigDecimal.ZERO);}
		       
		      return member;
		        		
	    }
/*
	    @Autowired
	    public List<Member> getMembersList() {
	        String sql = " select * from adherent";
	        Session session = this.sessionFactory.getCurrentSession();
	        Query  query = session.createQuery(sql);
	        System.out.println(query.getResultList().size());
	        return query.getResultList();
	        
	        
	    }*/
}
