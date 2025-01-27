package com.digital_bank._bit.account.controller;

import com.digital_bank._bit.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/balance")
    public BigDecimal getBalance(@PathVariable Long accountId){
        return accountService.getBalance(accountId);
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount){
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
    }
    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccountId, toAccountId, amount);
    }

}
