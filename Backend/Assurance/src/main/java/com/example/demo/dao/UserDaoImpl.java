package com.example.demo.dao;

import java.math.BigDecimal;

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
	      	  SQLQuery  query = session.createSQLQuery(sql);
	         logger.info(query.getQueryString());
	        
	         Object ligneAsObject = query.getResultList().get(0);

	             // ligne correspond à une des lignes du résultat
	            Object[] ligne = (Object[]) ligneAsObject ;
	        	
	            user.setId((BigDecimal)ligne[0]);
	            user.setUsername((String)ligne[1]);
	            user.setPassword((String)ligne[2]);
	            user.setRole((String)ligne[3]);
	            user.setPonum((BigDecimal)ligne[4]);
	            user.setMat((String)ligne[5]);
	         return user;    
   	 }catch(Exception e){
   		 logger.error(e.getMessage()); 
   		 user=null;
   	 }
	      
	     	    	return user;	
   }
	
	
	public int changePassword(String oldPassword, String newPassword, BigDecimal userId ) {
		logger.info("old "+oldPassword);
		logger.info("new "+newPassword);
		logger.info("userId "+userId);
		 String sql = "select * from appuser where id="+userId;
	   	 Session session;
	   	 int result=0;
		 try{
	         session = this.sessionFactory.openSession();
	      	  SQLQuery  query = session.createSQLQuery(sql);
	         logger.info(query.getQueryString());
	         Object ligneAsObject = query.getSingleResult();

	             // ligne correspond à une des lignes du résultat
	            Object[] ligne = (Object[]) ligneAsObject ;
	            String currentPassword=(String)ligne[2];
	            if(currentPassword.equals(oldPassword)) {
	            	String sqlUpdate="update appuser set password='"+newPassword+"' where id="+userId;
	            			SQLQuery  queryUpdate = session.createSQLQuery(sqlUpdate);
	            			 result = queryUpdate.executeUpdate();
	            }
	        	
   	 }catch(Exception e){
   		 logger.error(e.getMessage()); 
   	 }
		 return result;
		 
	}

	public int addUser(String newPassword, String username, BigDecimal ponum, String mat) {
		logger.info("new " + newPassword);
		logger.info("ponum " + ponum);
		String sql = "insert into appuser values (seq_user.nextval, :username , :newPassword, 'member', :ponum, :mat)";
		Session session;
		int result = 0;
		try {
			session = this.sessionFactory.openSession();

			Transaction tx = null;
			tx = (Transaction) session.beginTransaction();

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("username", username);
			query.setParameter("newPassword", newPassword);
			query.setParameter("ponum", ponum);
			query.setParameter("mat", mat);

			logger.info(query.getQueryString());

			result = query.executeUpdate();
			
			tx.commit(); // this is important
	session.close();
			logger.info("after update"+result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;

	}




}