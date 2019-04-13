package com.example.demo.dao;

import java.math.BigDecimal;

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
import com.example.demo.entities.User;

@Service
@Transactional 
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	  @Autowired
	    private final SessionFactory sessionFactory=null;
	  
	  @Autowired
	    public UserDaoImpl() {
	    
	    }
	  Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findByUsername(String username) {
		User user = new User();
		return user;
	}
	
	
	public User signIn(String username, String password) {

   	 String sql = "select * from appuser where username='"+username+"' and password='"+password+"'";
   	 Session session;
     User user =new User();
   	 try{
	         session = this.sessionFactory.openSession();
	         session = this.sessionFactory.openSession();
	      	  SQLQuery  query = session.createSQLQuery(sql);
	         logger.info(query.getQueryString());
	        
	         Object ligneAsObject = query.getSingleResult();

	             // ligne correspond à une des lignes du résultat
	            Object[] ligne = (Object[]) ligneAsObject ;
	        	
	            user.setId((BigDecimal)ligne[0]);
	            user.setUsername((String)ligne[1]);
	            user.setPassword((String)ligne[2]);
	            user.setRole((String)ligne[3]);
	            user.setMemberId((BigDecimal)ligne[4]);
	         return user;    
   	 }catch(Exception e){
   		 logger.error(e.getMessage()); 
   		 user=null;
   	 }
	      
	     	    	return user;	
   }

}