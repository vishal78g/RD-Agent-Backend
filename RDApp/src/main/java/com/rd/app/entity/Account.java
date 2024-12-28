package com.rd.app.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
	
	@Id
	@Column(name = "account_number")
	private long accountNumber;
	
	private String name;
	
	private double amount;
	
	private String village;
	
	@Column(name = "mobile_number")
	private long mobileNumber;
	
	private int monthPaid;
	
	private String nextInstallment;

}
