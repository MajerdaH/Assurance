package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.demo.entities.Refund;


    
@Service
@Transactional 
@Repository("refundDao")	 
public class RefundDaoImpl implements RefundDao{
    	
    	  @Autowired
  	    private final SessionFactory sessionFactory=null;

  	   @Autowired
  	    public RefundDaoImpl() {
  	    
  	    }
	public List<Refund> getRefundsByMatAndPonum(BigDecimal ponum,String matricule){
		
		List<Refund> refunds = new ArrayList<Refund>();
		

	    Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);
		
		 String sql = "select * from princsin where wpol='"+ponum +"' and w_mat='"+matricule+"'";
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
               Refund refund = new Refund();
	           Object[] ligne = (Object[]) ligneAsObject ;
	       	refund.setCompanyPolice((String)ligne[0]);
	       	refund.setMembetMat((String)ligne[1]);
	       	refund.setwCode((String)ligne[2]);
	           refund.setCareDate((String)ligne[3]);
	           refund.setPharmacyRub((String)ligne[4]);
	          // refund.setPharmacyRub((String)ligne[5]);
	           refund.setBulletinNumber(String.valueOf(ligne[6]));
	           refund.setBourderau((BigDecimal)ligne[7]);
	           refund.setEngagedUnities((BigDecimal)ligne[8]);
	           refund.setRefundedUnities((BigDecimal)ligne[9]);
	           refund.setPayedAmount((BigDecimal)ligne[10]);
	           refund.setRefundedAmount((BigDecimal)ligne[11]);
	           refund.setSettlementDate((String)ligne[13]);
	           refunds.add(refund);
	        }
	        }
		
		return refunds;
	}
}
