package com.example.demo.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<Refund> getRefundsByMatAndPonum(BigDecimal ponum,String matricule, BigDecimal nBull){
		
		List<Refund> refunds = new ArrayList<Refund>();
		

	    Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);
		
		 String sql = "select * from princsin where wpol='"+ponum +"' and w_mat='"+matricule+"' and w_bulp="+nBull;
    	 Session session;
    	 try{
	         session = this.sessionFactory.getCurrentSession();

    	 }catch(Exception e){
    		 session = this.sessionFactory.openSession();
    	 }
	        SQLQuery  query = session.createSQLQuery(sql);
	       logger.info("result"+query.getResultList().size());
	        if(query.getResultList().size()>0){
	        	 DateFormat formatter = new SimpleDateFormat("yyyyMMDD"); 
	        	 SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
	        	 
	        for (Object ligneAsObject : query.getResultList()) {
               Refund refund = new Refund();
	           Object[] ligne = (Object[]) ligneAsObject ;
	       	refund.setCompanyPolice((String)ligne[0]);
	       	refund.setMembetMat((String)ligne[1]);
	       	refund.setwCode((String)ligne[2]);
	          
	      
	           
	 
	           refund.setPharmacyRub((String)ligne[4]);
	           refund.setBulletinNumber(String.valueOf(ligne[6]));
	           refund.setBourderau((BigDecimal)ligne[7]);
	           refund.setEngagedUnities((BigDecimal)ligne[8]);
	           refund.setRefundedUnities((BigDecimal)ligne[9]);
	           refund.setPayedAmount((BigDecimal)ligne[10]);
	           refund.setRefundedAmount((BigDecimal)ligne[11]);
	           
			try { 
				Date date;
				Date 	cdate;
				cdate= (Date)formatter.parse((String)ligne[3]);
				date = (Date)formatter.parse((String)ligne[13]);
		           String finalString = newFormat.format(date);
		           String finalString2 = newFormat.format(cdate);
		           refund.setCareDate(finalString2);
		           refund.setSettlementDate(finalString);
		           
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
	           refunds.add(refund);
	        }
	        }
		
		return refunds;
	}
	
public List<Refund> getBullByMatAndPonum(BigDecimal ponum,String matricule){
		
		List<Refund> refunds = new ArrayList<Refund>();
		

	    Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);
		
		 String sql = "select  W_BULP, sum(W_MBP), sum(W_MBR), W_DAT from princsin where w_mat='"+matricule+"' and wpol="+ponum+" group by W_BULP, W_DAT";
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
	       	refund.setCompanyPolice(ponum.toString());
	       	refund.setMembetMat(matricule);
	           refund.setBulletinNumber(String.valueOf(ligne[0]));
	           refund.setPayedAmount((BigDecimal)ligne[1]);
	           refund.setRefundedAmount((BigDecimal)ligne[2]);
	           
	           String start_dt = (String)ligne[3];
	           DateFormat formatter = new SimpleDateFormat("yyyyMMDD"); 
	           Date date;
			try {
				date = (Date)formatter.parse(start_dt);
				SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
		           String finalString = newFormat.format(date);
		           refund.setSettlementDate(finalString);
		           
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           refunds.add(refund);
	        }
	        }
		
		return refunds;
	}
}
