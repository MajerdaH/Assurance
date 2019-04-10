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

	    Logger logger = LoggerFactory.getLogger(MemberDao.class);
	 
	 
	    public Member findById() {
	    	 logger.info("8888888888888888888888888888888");

	    	 String sql = "select * from adherent where nump=111";
	    	 Session session;
	    	 try{
	    		  logger.info("6666666666666666666666666666666666666");
		         session = this.sessionFactory.openSession();
		         logger.info("777777777777777777777777777777777777777");

	    	 }catch(Exception e){
	    		 logger.debug("999999999999999999999999999999999999");
	    		 session = this.sessionFactory.openSession();
	    	 }
		        SQLQuery  query = session.createSQLQuery(sql);
		        logger.info("333333333333333333333333"+query.toString());
		        logger.info(query.getQueryString());
		        logger.info("8888888888"+query.getResultList().size());
		        Member member =new Member();
		       
		        for (Object ligneAsObject : query.getResultList()) {

		            // ligne correspond à une des lignes du résultat
		           Object[] ligne = (Object[]) ligneAsObject ;
		       	
		            // cette liste est composée de deux éléments : nom et prenom
		           member.setNumP((BigDecimal)ligne[0] );
		           member.setMat((String)ligne[1]) ;
		           member.setNom((String)ligne[2]);
		           member.setPrenom((String)ligne[3]);
		           member.setDateN((String)ligne[4]);
		       
		       }
		      
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
