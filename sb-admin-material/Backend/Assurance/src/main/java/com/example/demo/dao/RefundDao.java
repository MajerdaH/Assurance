package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import com.example.demo.entities.Refund;

public interface RefundDao {
	
	public List<Refund> getRefundsByMatAndPonum(BigDecimal ponum,String matricule);

}
