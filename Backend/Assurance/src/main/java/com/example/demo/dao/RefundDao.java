package com.example.demo.dao;

import java.util.List;
import com.example.demo.entities.Refund;

public interface RefundDao {
	
	public List<Refund> getRefundsByMat(String matricule);

}
