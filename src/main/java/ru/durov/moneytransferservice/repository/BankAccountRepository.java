package ru.durov.moneytransferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.durov.moneytransferservice.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
