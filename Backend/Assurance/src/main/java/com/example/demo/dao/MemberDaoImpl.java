package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Member;

@Repository
	@Transactional
	public class MemberDaoImpl  implements MemberDao {
	 
	    @Autowired
	    private final SessionFactory sessionFactory;

	    @Autowired
	    public MemberDaoImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    Logger logger = LoggerFactory.getLogger(MemberDao.class);
	 
	    
	    public Member findById(Long id) {
	    	 String sql = " select * from adherent where nump="+id;
		        Session session = this.sessionFactory.getCurrentSession();
		        Query  query = session.createQuery(sql);
		        logger.debug(query.getQueryString());
		        logger.info(query.getQueryString());
		        logger.info(sql, query.getResultList().size());
		        return (Member) query.getResultList().get(0);
	    }

	    
	    public List<Member> getMembersList() {
	        String sql = " select * from adherent";
	        Session session = this.sessionFactory.getCurrentSession();
	        Query  query = session.createQuery(sql);
	        System.out.println(query.getResultList().size());
	        return query.getResultList();
	        
	        
	    }
}
