package ru.durov.moneytransferservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.durov.moneytransferservice.model.BankAccount;
import ru.durov.moneytransferservice.model.Card;
import ru.durov.moneytransferservice.service.CardService;
import ru.durov.moneytransferservice.util.StringDateFormatter;

import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {
    private final StringDateFormatter stringDateFormatter;
    private final CardService cardService;

    public AppRunner(StringDateFormatter stringDateFormatter, CardService cardService) {
        this.stringDateFormatter = stringDateFormatter;
        this.cardService = cardService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        BankAccount firstBankAccount = BankAccount.builder()
                .accountNumber("40702865400000000222")
                .currency("RUR")
                .value(BigDecimal.valueOf(10100000))
                .build();
        BankAccount secondBankAccount = BankAccount.builder()
                .accountNumber("40702865900000000333")
                .currency("RUR")
                .value(BigDecimal.valueOf(10200000))
                .build();
        BankAccount thirdBankAccount = BankAccount.builder()
                .accountNumber("40702898300000000444")
                .currency("RUR")
                .value(BigDecimal.valueOf(10300000))
                .build();

        Card firstCard = Card.builder()
                .cardNumber("1111111111111111")
                .validTill(stringDateFormatter.formatStringToDate("01/23"))
                .CVV("017")
                .bankAccount(firstBankAccount)
                .build();
        Card secondCard = Card.builder()
                .cardNumber("2222222222222222")
                .validTill(stringDateFormatter.formatStringToDate("02/23"))
                .CVV("018")
                .bankAccount(secondBankAccount)
                .build();
        Card thirdCard = Card.builder()
                .cardNumber("3333333333333333")
                .validTill(stringDateFormatter.formatStringToDate("03/23"))
                .CVV("019")
                .bankAccount(thirdBankAccount)
                .build();

        cardService.saveCard(firstCard);
        cardService.saveCard(secondCard);
        cardService.saveCard(thirdCard);
    }
}
