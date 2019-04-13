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
	    
	    private BigDecimal memberId;


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

		public BigDecimal getMemberId() {
			return memberId;
		}

		public void setMemberId(BigDecimal memberId) {
			this.memberId = memberId;
		}

	}