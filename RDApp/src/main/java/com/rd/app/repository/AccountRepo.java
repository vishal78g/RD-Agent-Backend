package com.rd.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rd.app.entity.Account;

public interface AccountRepo extends JpaRepository<Account,	 Long>{

	
	List<Account> findByNameContainingIgnoreCase(String name);
	
	List<Account> findByVillageContainingIgnoreCase(String village);
	
	List<Account> findByAccountNumber(long accountNumber);

	List<Account> findByMobileNumber(long mobileNumber);
	
	List<Account> findByAmount(double amount);
	
	

}
