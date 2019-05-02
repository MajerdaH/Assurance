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

	 Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	public List<Claim> getClaimsByClientMat(String matricule, BigDecimal ponum){
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
	           claim.setDateClaim((Date)ligne[3]);
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
	
	
	public int addClaim(Claim claim) {
		
		/*int i=0;
		if (claim.isStatus()) i=1; else i=0;
		 String sql = "insert into reclamation (type, description,daterec, status,piecejointe, mat) "+
	"values ('"+claim.getDescription()+"',"+claim.getDateClaim()+","+i+",'"+claim.getJoinedFile()+"','"+claim.getMatricule()+"')";
	*/int i=0;
    	 Session session;
    	 try{
	         session = this.sessionFactory.getCurrentSession();
	         session.save(claim);
	          i=1;

    	 }catch(Exception e){
    		// session = this.sessionFactory.openSession();
    		 logger.error(e.getMessage());
    	 }
	     return i;  
	}
	
	public int modifyClaim(String response, String status) {
		
		int i=1;
		return i;
	}

}
