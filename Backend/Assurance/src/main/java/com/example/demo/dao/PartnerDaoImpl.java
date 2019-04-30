package com.example.demo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
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
	    	
	public List<Partner> getPartnersByMember(String matricule, BigDecimal ponum){
		List <Partner> partners= new ArrayList<Partner>();
		
		
		logger.info("mat "+matricule);
   	 String sql = "select * from presta where adpoma='0"+ponum.toString()+matricule+"'" ;
   	 Session session;
   	 try{
	         session = this.sessionFactory.getCurrentSession();

   	
	        SQLQuery  query = session.createSQLQuery(sql);
	       logger.info("result"+query.getResultList().size());
	        if(query.getResultList().size()>0){
	        	logger.info("size>0");
	        for (Object ligneAsObject : query.getResultList()) {
	        	Partner part= new Partner();

	           Object[] ligne = (Object[]) ligneAsObject ;
	           part.setAdpoma((String)ligne[0]);
	           part.setPonum((BigDecimal)ligne[1]);
	           part.setPrpomt((String)ligne[2]);
	          part.setDateN((String)ligne[3]);
	          part.setSitm((String)ligne[4]);
	          part.setType((BigDecimal)ligne[5]);
	          part.setPrpre((String)ligne[10]);
	        
	        partners.add(part);   
	        }}
	        }catch(Exception e){logger.error(e.getMessage());}

		return partners;
	}
	
	public int setPartnersInfos(List<Partner>children, Partner wife, BigDecimal ponum, String mat) {
		//Update wife infos
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("update presta set PRDATN='"+wife.getDateN().toString());
		//sqlUpdate.append(", ")
		//+"' where id="+userId;
		int result=0;
		 Session session;
    	 session = this.sessionFactory.getCurrentSession();

SQLQuery  queryUpdate = session.createSQLQuery(sqlUpdate.toString());
 result = queryUpdate.executeUpdate();
		 
		 return result;
		
	}


	
	//modifyPartnerInfo(String matricule, )
}
