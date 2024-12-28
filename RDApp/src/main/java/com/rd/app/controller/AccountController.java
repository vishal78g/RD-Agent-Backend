package com.rd.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rd.app.entity.Account;
import com.rd.app.payload.Search;
import com.rd.app.service.AccountServiceImpl;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;
	
	//create account post
	@PostMapping("/add")
	public ResponseEntity<Account> addAccount(@RequestBody Account account){
		Account cretedAccount = this.accountService.creteAccount(account);
		return ResponseEntity.ok(cretedAccount);
	}

	
	//update account post
	@PostMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account,@PathVariable String accountNumber){
		Long accNumber=Long.parseLong(accountNumber);
		Account updateAccount = this.accountService.updateAccount(accNumber, account);
		return ResponseEntity.ok(updateAccount);
	}
	
	//get account get
	
	//get all accounts get
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<Account> listAccounts() {
		List<Account> accounts = this.accountService.getAllAccounts();
		return accounts;
	}
	
	@PostMapping("/search")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<Account> searchAccounts(@RequestBody Search search){
		List<Account> accounts = this.accountService.getSearchedAccounts(search);
		return accounts;
	}
	
	
	
	//delete account post
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAcc(@PathVariable Long accountNumber){
		this.accountService.deleteAccount(accountNumber);
		return ResponseEntity.ok("Account deleted..!");
	}
	
	@PostMapping("/receive-data")
	@CrossOrigin(origins = "chrome-extension://gajjnpkfijdhmkadgkfgkakbohjphdfi")
	public ResponseEntity<String> receiveData(@RequestBody Map<String, List<String[]> > payload) {
        List<String[]> data = (List<String[]>) payload.get("data");
        System.out.println("Received data: " + data.get(1));
        // Process and store the data
        this.accountService.updateAllAccounts(data);
        return ResponseEntity.ok("Data received successfully");
    }
	
}
