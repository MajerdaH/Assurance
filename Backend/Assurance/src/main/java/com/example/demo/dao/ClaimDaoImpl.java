package com.example.demo.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Claim;
import com.example.demo.entities.Member;


	@Service
	@Transactional 
	@Repository("claimDao")
	public class ClaimDaoImpl implements ClaimDao {
		 
		    @Autowired
		    private final SessionFactory sessionFactory=null;

		   @Autowired
		    public ClaimDaoImpl() {
		    
		    }

	 Logger logger = LoggerFactory.getLogger(ClaimDaoImpl.class);
	
	public List<Claim> getClaimsByClientMat(String matricule, BigDecimal ponum) throws ParseException{
		List<Claim> clientClaims = new ArrayList<Claim>();
		
		 String sql = "select * from reclamation where mat='"+matricule+"' and ponum="+ponum;
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
	        	Claim claim = new Claim();

	           Object[] ligne = (Object[]) ligneAsObject ;
	       	
	           claim.setId((BigDecimal)ligne[0]);
	           claim.setType((String)ligne[1]);
	           claim.setDescription((String)ligne[2]);
	           Timestamp ts=(Timestamp)ligne[3];

	       	
	       	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	       	Date date=new Date(ts.getTime()); 
	      // 	logger.info(format.parse(format.format(date)).toString());
	       	logger.info(format.format(date));
	           claim.setDateClaim(format.format(date));
	           BigDecimal i = (BigDecimal)ligne[4];
	           if (i.equals(0)){
	        	   claim.setStatus(false);
	           }else if(i.equals(1)){
	        	   claim.setStatus(true);
	           }
	           claim.setJoinedFile((String)ligne[5]) ;
	           claim.setMatricule((String)ligne[6]);
	           claim.setPonum((BigDecimal)ligne[7]);
	           clientClaims.add(claim);
	        }
	        }
	      return clientClaims;
	}
	
	
	public int addClaim(String type, String description, String mat, BigDecimal ponum, String joinedFile) {
	Date d = new Date();
	Date   date =null;
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	String dateString = format.format( new Date());
	logger.info(dateString);
	try {
   date   = format.parse (dateString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
		StringBuilder builder= new StringBuilder();
		 builder.append("insert into reclamation values (seq_reclamation.nextval,'"+type+"', '"+description+"','"+dateString+"',0,'"+joinedFile+"', '"+mat+"',"+ponum+")") ;
    	 
    	 Session  session = this.sessionFactory.openSession();
		   	
	   	 Transaction tx = null;
	   	 tx=session.beginTransaction();
	   	SQLQuery query = session
                .createSQLQuery(builder.toString());
	   	logger.info(query.getQueryString());
 		
    int resp=	query.executeUpdate();
    	logger.info("after insert");
        tx.commit(); // this is important
        session.clear();
	
	return resp;
	}
	

}
