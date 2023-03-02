package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {
    private BankAccountRepositories bankAccountRepositories;

    public AccountRestController(BankAccountRepositories bankAccountRepositories) {
        this.bankAccountRepositories = bankAccountRepositories;
    }
    @GetMapping("/BankAccounts")
    public List<BankAccount>bankAccounts(){
        return bankAccountRepositories.findAll();
    }
    @GetMapping("/BankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepositories.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/BankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        if(bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepositories.save(bankAccount);
    }
    @PutMapping("/BankAccounts/{id}")
    public BankAccount UpdateAccount(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepositories.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getCreatAt()!=null) account.setCreatAt(new Date());
        return bankAccountRepositories.save(account);
    }
    @DeleteMapping("/BankAccounts/{id}")
    public void DeleteAccount(@PathVariable String id) {
         bankAccountRepositories.deleteById(id);
    }
}
