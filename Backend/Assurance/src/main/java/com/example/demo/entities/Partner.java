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
@Table(name="PRESTA")
public class Partner implements Serializable {
	
    @SequenceGenerator(name = "presta_seq", sequenceName = "presta_seq")
    
      
    private String adpoma;
    
    @Id
    private String prpomt;
    
    @Column(name="PRPOMT")
    public String getPrpomt() {
		return prpomt;
	}


	public void setPrpomt(String prpomt) {
		this.prpomt = prpomt;
	}

    public String getAdpoma() {
		return adpoma;
	}


	public void setAdpoma(String adpoma) {
		this.adpoma = adpoma;
	}


	public String getDateN() {
		return dateN;
	}


	public void setDateN(String dateN) {
		this.dateN = dateN;
	}


	public BigDecimal getPonum() {
		return ponum;
	}


	public void setPonum(BigDecimal ponum) {
		this.ponum = ponum;
	}


	public BigDecimal getType() {
		return type;
	}


	public void setType(BigDecimal type) {
		this.type = type;
	}


	public String getSitm() {
		return sitm;
	}


	public void setSitm(String sitm) {
		this.sitm = sitm;
	}


	public BigDecimal getConp() {
		return conp;
	}


	public void setConp(BigDecimal conp) {
		this.conp = conp;
	}


	public String getPrpre() {
		return prpre;
	}


	public void setPrpre(String prpre) {
		this.prpre = prpre;
	}


	public String getPrdatem() {
		return prdatem;
	}


	public void setPrdatem(String prdatem) {
		this.prdatem = prdatem;
	}

	private String dateN;
    private BigDecimal ponum;
    private BigDecimal type;
    private String sitm;
    private BigDecimal conp;
   
    private String prpre;
    private String prdatem;
    
 }
