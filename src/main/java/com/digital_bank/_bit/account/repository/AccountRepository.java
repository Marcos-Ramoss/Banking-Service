package com.digital_bank._bit.account.repository;

import com.digital_bank._bit.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
