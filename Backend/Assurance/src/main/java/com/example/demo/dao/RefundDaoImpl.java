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
public class RefundDaoImpl implements RefundDao {

	@Autowired
	private final SessionFactory sessionFactory = null;

	@Autowired
	public RefundDaoImpl() {

	}

	public List<Refund> getRefundsByMatAndPonum(BigDecimal ponum, String matricule, BigDecimal nBull) {

		List<Refund> refunds = new ArrayList<Refund>();

		Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);
		StringBuilder builder = new StringBuilder();
		builder.append(
				"select p.*,  m.rrub, m.rjj, m.rjr, m.rmp, m.rmb, m.mv, m.matt,m.rref, m.sdent,m.filler1, m.rmm, m.rnom from princsin p ,"
				+ " MVTSINTS m where p.w_bulp=m.rbul and p.wpol="+ponum+" and p.w_mat='"+matricule+"' and p.w_bulp="+nBull);
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();

		} catch (Exception e) {
			session = this.sessionFactory.openSession();
		}
		SQLQuery query = session.createSQLQuery(builder.toString());
		/*query.setParameter("ponum", ponum);
		query.setParameter("matricule", matricule);
		query.setParameter("nBull", nBull);*/
		logger.info("result" + query.getResultList().size());
		if (query.getResultList().size() > 0) {
			DateFormat formatter = new SimpleDateFormat("yyyyMMDD");
			SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

			for (Object ligneAsObject : query.getResultList()) {
				Refund refund = new Refund();
				Object[] ligne = (Object[]) ligneAsObject;
				refund.setCompanyPolice((String) ligne[0]);
				refund.setMembetMat((String) ligne[1]);
				refund.setwCode((String) ligne[2]);
        String f=(String) ligne[4];
        logger.info(f);
        if (f.contains("C1")){refund.setPharmacyRub("Visite Spécialiste");
        logger.info("yes");
        }
else if(f.contains("PH")){refund.setPharmacyRub("Frais Pharmacie");
logger.info("phar");
}
				refund.setBulletinNumber(String.valueOf(ligne[6]));
				refund.setBourderau((BigDecimal) ligne[7]);
				refund.setEngagedUnities((BigDecimal) ligne[8]);
				refund.setRefundedUnities((BigDecimal) ligne[9]);
				refund.setPayedAmount((BigDecimal) ligne[10]);
				refund.setRefundedAmount((BigDecimal) ligne[11]);
				String rrub = (String) ligne[17];

				if (rrub.equals("TX"))
					refund.setStatus("En attente");
				else if (rrub.equals("ZZZ"))
					refund.setStatus("Rejetée");
				else
					refund.setStatus("Traitée");

				StringBuilder buildMsg = new StringBuilder();
				
					buildMsg.append((String) ligne[18]);

					buildMsg.append((String) ligne[19]);

					buildMsg.append((String) ligne[20]);

					buildMsg.append((String) ligne[21]);

					buildMsg.append((String) ligne[22]);

					buildMsg.append((String) ligne[23]);

					buildMsg.append((String) ligne[24]);

					buildMsg.append((Character) ligne[25]);

					buildMsg.append((String) ligne[26]);

					buildMsg.append((String) ligne[27]);

					buildMsg.append((String) ligne[28]);
				
				
				logger.info(buildMsg.toString());
				refund.setMessage(buildMsg.toString());
				try {
					Date date;
					Date cdate;
					cdate = (Date) formatter.parse((String) ligne[3]);
					date = (Date) formatter.parse((String) ligne[13]);
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

	public List<Refund> getBullByMatAndPonum(BigDecimal ponum, String matricule) {

		List<Refund> refunds = new ArrayList<Refund>();

		Logger logger = LoggerFactory.getLogger(RefundDaoImpl.class);
		logger.info(ponum.toString());
		logger.info(matricule);
		

		StringBuilder sql= new StringBuilder();
		sql.append("select p.W_BULP, sum(p.W_MBP), sum(p.W_MBR), p.W_DAT, m.rrub ");
		sql.append("from princsin p,MVTSINTS m where p.w_bulp=m.rbul and p.w_mat='"+matricule+"' and wpol="+ponum+" group by p.W_BULP, p.W_DAT, m.rrub");
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();

		} catch (Exception e) {
			session = this.sessionFactory.openSession();
		}
		SQLQuery query = session.createSQLQuery(sql.toString());
		//query.setParameter("mat", matricule);
		//query.setParameter("ponum", ponum);
		logger.info("result" + query.getResultList().size());
		if (query.getResultList().size() > 0) {
			for (Object ligneAsObject : query.getResultList()) {
				Refund refund = new Refund();
				Object[] ligne = (Object[]) ligneAsObject;
				refund.setCompanyPolice(ponum.toString());
				refund.setMembetMat(matricule);
				refund.setBulletinNumber(String.valueOf(ligne[0]));
				refund.setPayedAmount((BigDecimal) ligne[1]);
				refund.setRefundedAmount((BigDecimal) ligne[2]);

				String start_dt = (String) ligne[3];
				
				String rrub = (String) ligne[4];

				if (rrub.equals("TX"))
					refund.setStatus("En attente");
				else if (rrub.equals("ZZZ"))
					refund.setStatus("Rejetée");
				else
					refund.setStatus("Traitée");
				DateFormat formatter = new SimpleDateFormat("yyyyMMDD");
				Date date;
				try {
					date = (Date) formatter.parse(start_dt);
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
