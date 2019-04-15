package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Member;
import com.example.demo.entities.Partner;
	    
@Service
@Transactional 
@Repository("partnerDao")	 
public class PartnerDaoImpl implements PartnerDao{
	    	
	    	  @Autowired
	  	    private final SessionFactory sessionFactory=null;

	  	   @Autowired
	  	    public PartnerDaoImpl() {
	  	    
	  	    }

	
	Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	    	
	public List<Partner> getPartnersByMemberId(String matricule){
		List <Partner> partners= new ArrayList<Partner>();
		
		
		logger.info("mat "+matricule);
   	 String sql = "select * from presta where nump="+matricule;
   	 Session session;
   	 try{
	         session = this.sessionFactory.getCurrentSession();

   	 }catch(Exception e){
   		 session = this.sessionFactory.openSession();
   	 }
	        SQLQuery  query = session.createSQLQuery(sql);
	       logger.info("result"+query.getResultList().size());
	        if(query.getResultList().size()>0){
	        for (Object ligneAsObject : query.getResultList()) {
	        	Partner part= new Partner();

	           Object[] ligne = (Object[]) ligneAsObject ;
	       	
	         /*  partner.setNumP((BigDecimal)ligne[0] );
	           member.setMat((String)ligne[1]) ;
	           member.setNom((String)ligne[2]);
	           member.setPrenom((String)ligne[3]);
	           member.setDateN((String)ligne[4]);*/
	        partners.add(part);   
	        }
	        }

		return partners;
	}

	
	//modifyPartnerInfo(String matricule, )
}
