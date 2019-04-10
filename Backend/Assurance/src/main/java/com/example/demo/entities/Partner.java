package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRESTA")
public class Partner implements Serializable {
	
    @SequenceGenerator(name = "presta_seq", sequenceName = "presta_seq")
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "partner_Sequence")
    private Long id;
    private char adpoma;
    private char prpomt;
    
    @Column(name="PRPOMT")
    public char getPrpomt() {
		return prpomt;
	}


	public void setPrpomt(char prpomt) {
		this.prpomt = prpomt;
	}

	private int matricule;
    private String dateN;
    private char sit;
    private int type;
    private char sitm;
    private String data;
    private int conc;
    private int conp;
    private String pre;
    private String dateS;
    
    
 @Column(name="PRESTA_ID")   
 public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="ADPOMA")
	public char getAdpoma() {
		return adpoma;
	}


	public void setAdpoma(char adpoma) {
		this.adpoma = adpoma;
	}


    @Column(name="PONUM")
	public int getMatricule() {
		return matricule;
	}


	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}


	@Column(name="PRDATN")
	public String getDateN() {
		return dateN;
	}


	public void setDateN(String dateN) {
		this.dateN = dateN;
	}


	@Column(name="PRSIT")
	public char getSit() {
		return sit;
	}


	public void setSit(char sit) {
		this.sit = sit;
	}


	@Column(name="PRTYP")
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	@Column(name="PRSITM")
	public char getSitm() {
		return sitm;
	}


	public void setSitm(char sitm) {
		this.sitm = sitm;
	}


	@Column(name="PRDATA")
	public String getData() {
		return data;
	}
	
	
	public void setData(String data) {
		this.data = data;
	}


	@Column(name="PRCONC")
	public int getConc() {
		return conc;
	}


	public void setConc(int conc) {
		this.conc = conc;
	}


	@Column(name="PRCONP")
	public int getConp() {
		return conp;
	}


	public void setConp(int conp) {
		this.conp = conp;
	}


	@Column(name="PRPRE")
	public String getPre() {
		return pre;
	}


	public void setPre(String pre) {
		this.pre = pre;
	}


	@Column(name="PRDATS")
	public String getDateS() {
		return dateS;
	}


	public void setDateS(String dateS) {
		this.dateS = dateS;
	}
}
