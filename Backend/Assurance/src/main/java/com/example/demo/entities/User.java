package com.example.demo.entities;
	
	import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

	@Entity
	@Table(name = "user")
	public class User {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private BigDecimal id;

	    private String username;

	    private String password;
	    
	    private String role; 
	    
	    private BigDecimal ponum;
	    
	    private String mat;


	    public BigDecimal getId() {
	        return id;
	    }

	    public void setId(BigDecimal id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public BigDecimal getPonum() {
			return ponum;
		}

		public void setPonum(BigDecimal ponum) {
			this.ponum = ponum;
		}

		public String getMat() {
			return mat;
		}

		public void setMat(String mat) {
			this.mat = mat;
		}

	}