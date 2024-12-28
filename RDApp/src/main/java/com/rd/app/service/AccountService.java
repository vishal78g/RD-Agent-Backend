package com.rd.app.service;

import java.util.List;

import com.rd.app.entity.Account;
import com.rd.app.payload.Search;

public interface AccountService {
	
	//crete
	public Account creteAccount(Account acc);
	
	
	//update
	public Account updateAccount(Long accountNumber, Account acc);
	
	
	//get 
	public Account getAccount(Long accountNumber);
	
	
	//get all
	public List<Account> getAllAccounts();
	
	
	//delete
	public void deleteAccount(Long accountNumber);
	
	
	//search
	public List<Account> getSearchedAccounts(Search search);
	
	//update accounts from DOP Agent portal
	public void updateAllAccounts(List<String[]> data);
	
}
