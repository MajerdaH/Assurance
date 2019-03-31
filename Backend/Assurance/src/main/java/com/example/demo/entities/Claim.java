package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Reclamation")
public class Claim {
	@SequenceGenerator(name = "reclamation_seq", sequenceName = "reclamation_seq")
	 
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "reclamation_seq")  	
	private int id;
	
	private String type;
	private String description;
	private Date dateClaim;
	private boolean status;
	private String joinedFile;
	
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="DATEREC")
	public Date getDateClaim() {
		return dateClaim;
	}
	public void setDateClaim(Date dateClaim) {
		this.dateClaim = dateClaim;
	}
	
	@Column(name="STATUS")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name="PIECEJOINTE")
	public String getJoinedFile() {
		return joinedFile;
	}
	public void setJoinedFile(String joinedFile) {
		this.joinedFile = joinedFile;
	}


}
