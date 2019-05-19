package com.example.demo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.example.demo.entities.Partner;

@Service
@Transactional
@Repository("partnerDao")
public class PartnerDaoImpl implements PartnerDao {

	@Autowired
	private final SessionFactory sessionFactory = null;

	@Autowired
	public PartnerDaoImpl() {

	}

	Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);

	public List<Partner> getPartnersByMember(String matricule, BigDecimal ponum) {
		List<Partner> partners = new ArrayList<Partner>();

		logger.info("mat " + matricule);
		String sql = "select * from presta where adpoma='0" + ponum.toString() + matricule + "'";
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();

			SQLQuery query = session.createSQLQuery(sql);
			logger.info("result" + query.getResultList().size());
			if (query.getResultList().size() > 0) {
				logger.info("size>0");
				for (Object ligneAsObject : query.getResultList()) {
					Partner part = new Partner();

					Object[] ligne = (Object[]) ligneAsObject;
					part.setAdpoma((String) ligne[0]);
					part.setPonum((BigDecimal) ligne[1]);
					part.setPrpomt((String) ligne[2]);
					part.setDateN((String) ligne[3]);
					part.setSitm((String) ligne[4]);
					part.setType((BigDecimal) ligne[5]);
					part.setPrpre((String) ligne[10]);

					partners.add(part);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return partners;
	}

	public int setPartnersInfos(List<Partner> children, Partner wife, BigDecimal ponum, String mat) {
		// Update wife infos
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append("update presta set PRDATN='" + wife.getDateN().toString());
		// sqlUpdate.append(", ")
		// +"' where id="+userId;
		int result = 0;
		Session session;
		session = this.sessionFactory.getCurrentSession();

		SQLQuery queryUpdate = session.createSQLQuery(sqlUpdate.toString());
		result = queryUpdate.executeUpdate();

		return result;

	}

	public int insertPartner(BigDecimal type, BigDecimal ponum, String mat, String dateN, String name, String adpoma,
			String prpomt, String sit) {
		int resp = 0;
		StringBuilder builder = new StringBuilder();
		builder.append(
				"insert into PRESTA (ADPOMA, PONUM, PRPOMT, PRDATN, PRSIT,PRTYP,PRSITM,PRDATA,PRCONC,PRCONP,PRPRE ");
		// PRDATS PRMEG
		builder.append(", PRETA, PRCPH)");
		// PRCLABO PRCFIL1 PRCFIL2 PRSDC PRPDC PROMC PROMP
		// PROVC PROVP PRMS PRANAC PRANAP PRPHCC PRPHCP PRORTC PRORTP PRCONSC PRCONSP
		// NBULTIN
		// String sql = "insert into PRESTA values (seq_user.nextval, :username ,
		// :newPassword, 'member', :ponum, :mat)";
		builder.append(" values(:adpoma, :ponum, :prpomt, :prdatn, :prsit, :prtyp, :prsitm, :prdata, :prconc, ");
		builder.append(":prconp, :prpre, :preta, :prcph )");
		Session session;
		int result = 0;
		//Date d = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		String dateString = format.format( new Date());
		
		try {
			session = this.sessionFactory.openSession();

			Transaction tx = null;
			tx = (Transaction) session.beginTransaction();
			String t=type.toString() ;
			if (type.toString().length()>1) t="0"+t;

			SQLQuery query = session.createSQLQuery(builder.toString());
			query.setParameter("adpoma", "0" + ponum.toString() + mat);
			query.setParameter("prpomt", "0" + ponum.toString() + mat +t);
			query.setParameter("ponum", ponum);
			query.setParameter("prdatn", dateN);
			query.setParameter("prsit", sit);
			query.setParameter("prtyp", type);
			query.setParameter("prsitm", 0);
			query.setParameter("prdata", dateString);
			query.setParameter("prconc", 0);
			query.setParameter("prconp", 0);
			query.setParameter("prpre", name);
			query.setParameter("preta", "NO");
			query.setParameter("prcph", 0);

			result = query.executeUpdate();

			tx.commit(); // this is important
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public int deletePartner(String prpomt) {
		int resp = 0;
		Session session;
		StringBuilder builder = new StringBuilder();
		builder.append("delete from PRESTA where prpomt='" + prpomt + "'");
		session = this.sessionFactory.openSession();

		try {
			SQLQuery query = session.createSQLQuery(builder.toString());
			resp = query.executeUpdate();
			Transaction tx = null;
			tx = (Transaction) session.beginTransaction();
			tx.commit(); // this is important
			session.close();
			logger.info("after update" + resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return resp;
	}
}
