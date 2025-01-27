package com.digital_bank._bit.account.service;

import com.digital_bank._bit.account.model.Account;
import com.digital_bank._bit.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found"));

    }


    @Transactional
    public void deposit(Long accountId, BigDecimal amount){
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
    }


    @Transactional
    public void withdraw(Long accountId, BigDecimal amount){
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0){
           throw new RuntimeException("Insufficient funds");
        }
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        withdraw(fromAccountId, amount);
        deposit(toAccountId, amount);
    }
}
