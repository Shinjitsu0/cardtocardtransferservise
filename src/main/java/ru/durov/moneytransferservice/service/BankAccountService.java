package ru.durov.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.durov.moneytransferservice.model.BankAccount;
import ru.durov.moneytransferservice.model.Card;
import ru.durov.moneytransferservice.repository.BankAccountRepository;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public void save(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);

    }
}
