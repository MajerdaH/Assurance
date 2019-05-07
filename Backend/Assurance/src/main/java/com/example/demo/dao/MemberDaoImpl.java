package com.example.demo.dao;

import java.math.BigDecimal;

import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Member;
@Service
@Repository("memberDao")
	public class MemberDaoImpl  implements MemberDao {
	 
	    @Autowired
	    private final SessionFactory sessionFactory=null;

	   @Autowired
	    public MemberDaoImpl() {
	    
	    }

	    Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	 
	 
	    public Member findById(BigDecimal ponum, String mat) {
	    	//logger.info("id"+id);
	    	 String sql = "select * from adherent where nump="+ponum +" and mat='"+mat+"'";
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
		           member.setDateN((String)ligne[4]);
		        member.setRib((String)ligne[15]);   
		        member.setAddress((String)ligne[7]);   
		        member.setTel((String)ligne[13]);
		        }
		        }
		        else {member.setNumP(BigDecimal.ZERO);}
		       
		      return member;
		        		
	    }
	    
	    public int changeMemberInfos(BigDecimal ponum, String mat, String address, String phone, String rib)  {
	    	
	    	logger.info("address "+address);
			logger.info("phone "+phone);
			logger.info("rib "+rib);
			logger.info("ponum "+ponum);
			StringBuilder builder = new StringBuilder() ;
			builder.append("update ADHERENT set TEL=:phone");
		
			builder.append("  , RIB=:rib where ");
				builder.append("MAT=:mat and NUMP=:nump");
//		
		   	 Session  session = this.sessionFactory.openSession();
		   	
		   	 
		
		   	 Transaction tx = null;
		   	 tx=session.beginTransaction();
		   	SQLQuery query = session
	                .createSQLQuery(builder.toString());
		   	query.setParameter("rib", rib);
		   	query.setParameter("mat", mat);
		   	query.setParameter("phone", phone);
		   	query.setParameter("nump", ponum);
		   	 
	    int resp=	query.executeUpdate();
	    	logger.info("after update");
	        tx.commit(); // this is important
		        
		      	 
		    	
   		 logger.info("resp"+resp); 
//	   	 }
			 return resp;
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
