package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Refund {

	private String companyPolice;
	private String membetMat;
	private String wCode;
	private Date careDate;
	private String pharmacyRub;
	
	@Id
	private String bulletinNumber;
	private int bourderau;
	private int refundedUnities;
	private int engagedUnities;
	private float payedAmount;
	private float refundedAmount;
	private Date settlementDate;
	
	@Column(name = "WPOL")
	public String getCompanyPolice() {
		return companyPolice;
	}
	public void setCompanyPolice(String companyPolice) {
		this.companyPolice = companyPolice;
	}
	@Column(name = "W_MAT")
	public String getMembetMat() {
		return membetMat;
	}
	public void setMembetMat(String membetMat) {
		this.membetMat = membetMat;
	}
	@Column(name = "WP_PR")
	public String getwCode() {
		return wCode;
	}
	public void setwCode(String wCode) {
		this.wCode = wCode;
	}
	@Column(name = "W_DATE")
	public Date getCareDate() {
		return careDate;
	}
	public void setCareDate(Date careDate) {
		this.careDate = careDate;
	}
	@Column(name = "W_RUB")
	public String getPharmacyRub() {
		return pharmacyRub;
	}
	public void setPharmacyRub(String pharmacyRub) {
		this.pharmacyRub = pharmacyRub;
	}
	@Column(name = "W_BULP")
	public String getBulletinNumber() {
		return bulletinNumber;
	}
	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
	}
	
	@Column(name = "W_MRP")
	public int getBourderau() {
		return bourderau;
	}
	public void setBourderau(int bourderau) {
		this.bourderau = bourderau;
	}
	@Column(name = "W_JRP")
	public int getRefundedUnities() {
		return refundedUnities;
	}
	public void setRefundedUnities(int refundedUnities) {
		this.refundedUnities = refundedUnities;
	}
	@Column(name = "W_JJP")
	public int getEngagedUnities() {
		return engagedUnities;
	}
	public void setEngagedUnities(int engagedUnities) {
		this.engagedUnities = engagedUnities;
	}
	@Column(name = "W_MBP")
	public float getPayedAmount() {
		return payedAmount;
	}
	public void setPayedAmount(float payedAmount) {
		this.payedAmount = payedAmount;
	}
	@Column(name = "W_MBR")
	public float getRefundedAmount() {
		return refundedAmount;
	}
	public void setRefundedAmount(float refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	@Column(name = "DS")
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	
}
