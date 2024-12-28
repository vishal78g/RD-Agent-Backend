package com.rd.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.app.entity.Account;
import com.rd.app.payload.Search;
import com.rd.app.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Account creteAccount(Account acc) {
		Account createdAccount=this.accountRepo.save(acc);
		return createdAccount;
	}

	@Override
	public Account updateAccount(Long accountNumber, Account acc) {
		Account account=this.accountRepo.findById(accountNumber).orElseThrow();
		account.setName(acc.getName());
		account.setAmount(acc.getAmount());
		account.setVillage(acc.getVillage());
		account.setMobileNumber(acc.getMobileNumber());
		Account upadatedAccount = this.accountRepo.save(account);
		return upadatedAccount;
	}

	@Override
	public Account getAccount(Long accountNumber) {
		Account account=this.accountRepo.findById(accountNumber).orElseThrow();
		return account;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> list=this.accountRepo.findAll();
		return list;
	}

	@Override
	public void deleteAccount(Long accountNumber) {
		this.accountRepo.deleteById(accountNumber);
	}

	@Override
	public List<Account> getSearchedAccounts(Search search) {
		String field=search.getField();
		System.out.println(field);
		if (field.equals("name")) {
			List<Account> list = this.accountRepo.findByNameContainingIgnoreCase(search.getValue());
			return list;
		}else if (field.equals("village")) {
			List<Account> list = this.accountRepo.findByVillageContainingIgnoreCase(search.getValue());
			return list;
		}else if (field.equals("amount")) {
			Double amount=Double.parseDouble(search.getValue());
			List<Account> list = this.accountRepo.findByAmount(amount);
			return list;
		}else if (field.equals("accountNumber")) {
			Long accountNumber=Long.parseLong(search.getValue());
			List<Account> list = this.accountRepo.findByAccountNumber(accountNumber);
			return list;
		}else if (field.equals("mobileNumber")) {
			Long mobiletNumber=Long.parseLong(search.getValue());
			List<Account> list = this.accountRepo.findByMobileNumber(mobiletNumber);
			return list;
		}else {
			return null;			
		}
	}

	@Override
	public void updateAllAccounts(List<String[]> data) {
		List<Account> newList = new ArrayList<>();
		for (String[] acc : data) {
			Account account=mapper.map(acc, Account.class);
			newList.add(account);
		}
		System.out.println(newList);
		List<Account> saveAll = this.accountRepo.saveAll(newList);
		
	}

}
