package com.example.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Adherent")
public class Member implements Serializable{
		   
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "adherent_seq")
	 private BigDecimal numP;//ponum
	 
	 private String mat;
	 private String nom;
	 private String prenom;
	 private String DateN;
	 private char typeD;
	 private String numD;
	 private String address;
	 private String city;
	 private int cp;
	 private String motif;
	 private String joinDate;
	 private String redac;
	 private String tel;
	 private char sit;
	 private String rib;
	public BigDecimal getNumP() {
		return numP;
	}
	public void setNumP(BigDecimal numP) {
		this.numP = numP;
	}
	
	@Column(name="MAT")
	public String getMat() {
		return mat;
	}
	public void setMat(String mat) {
		this.mat = mat;
	}
	
	@Column(name="NOM")
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(name="PRENOM")
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Column(name="DATEN")
	public String getDateN() {
		return DateN;
	}
	public void setDateN(String dateN) {
		DateN = dateN;
	}
	
	@Column(name="TYPED")
	public char getTypeD() {
		return typeD;
	}
	public void setTypeD(char typeD) {
		this.typeD = typeD;
	}
	
	@Column(name="NUMD")
	public String getNumD() {
		return numD;
	}
	public void setNumD(String numD) {
		this.numD = numD;
	}
	
	@Column(name="ADR")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="VIL")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="CP")
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	@Column(name="MOTIF")
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	@Column(name="DATFIAB")
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Column(name="REDAC")
	public String getRedac() {
		return redac;
	}
	public void setRedac(String redac) {
		this.redac = redac;
	}
	@Column(name="TEL")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name="SIT")
	public char getSit() {
		return sit;
	}
	public void setSit(char sit) {
		this.sit = sit;
	}
	
	@Column(name="RIB")
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	 
}