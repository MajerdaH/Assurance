package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entities.Partner;

public interface PartnerDao {

	public List<Partner> getPartnersByMember(String matricule, BigDecimal ponum);

	public int setPartnersInfos(List<Partner>children, Partner wife, BigDecimal ponum, String mat);
	public int insertPartner(BigDecimal type, BigDecimal ponum, String mat, String dateN, String name, String adpoma,
			String prpomt, String sit);
}
