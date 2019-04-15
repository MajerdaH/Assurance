package com.example.demo.dao;

import java.util.List;

import com.example.demo.entities.Partner;

public interface PartnerDao {

	public List<Partner> getPartnersByMemberId(String matricule);
}
