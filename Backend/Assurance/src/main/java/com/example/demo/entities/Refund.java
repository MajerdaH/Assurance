package com.example.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Refund implements Serializable{

	private String companyPolice;
	private String membetMat;
	private String wCode;
	private String careDate;
	private String pharmacyRub;
	
	@Id
	private String bulletinNumber;
	private BigDecimal bourderau;
	private BigDecimal refundedUnities;
	private BigDecimal engagedUnities;
	private BigDecimal payedAmount;
	private BigDecimal refundedAmount;
	private String settlementDate;
	private String message;
	private String status;
	
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
	public String getCareDate() {
		return careDate;
	}
	public void setCareDate(String careDate) {
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
	public BigDecimal getBourderau() {
		return bourderau;
	}
	public void setBourderau(BigDecimal bourderau) {
		this.bourderau = bourderau;
	}
	@Column(name = "W_JRP")
	public BigDecimal getRefundedUnities() {
		return refundedUnities;
	}
	public void setRefundedUnities(BigDecimal refundedUnities) {
		this.refundedUnities = refundedUnities;
	}
	@Column(name = "W_JJP")
	public BigDecimal getEngagedUnities() {
		return engagedUnities;
	}
	public void setEngagedUnities(BigDecimal engagedUnities) {
		this.engagedUnities = engagedUnities;
	}
	@Column(name = "W_MBP")
	public BigDecimal getPayedAmount() {
		return payedAmount;
	}
	public void setPayedAmount(BigDecimal payedAmount) {
		this.payedAmount = payedAmount;
	}
	@Column(name = "W_MBR")
	public BigDecimal getRefundedAmount() {
		return refundedAmount;
	}
	public void setRefundedAmount(BigDecimal refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	@Column(name = "DS")
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
