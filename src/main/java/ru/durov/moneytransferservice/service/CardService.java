package ru.durov.moneytransferservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.durov.moneytransferservice.dto.CardDTO;
import ru.durov.moneytransferservice.exception.CardNotFoundException;
import ru.durov.moneytransferservice.exception.ConfirmationError;
import ru.durov.moneytransferservice.exception.InputDataError;
import ru.durov.moneytransferservice.model.BankAccount;
import ru.durov.moneytransferservice.model.Card;
import ru.durov.moneytransferservice.repository.CardRepository;
import ru.durov.moneytransferservice.util.StringDateFormatter;

import java.math.BigDecimal;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final StringDateFormatter stringDateFormatter;
    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    public CardService(CardRepository cardRepository, StringDateFormatter stringDateFormatter) {
        this.cardRepository = cardRepository;
        this.stringDateFormatter = stringDateFormatter;

    }

    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    public Card findCardByNumber(String cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber).orElseThrow(CardNotFoundException::new);

    }

    public void depositToCard(Card card, BigDecimal amount) {
        cardRepository.findCardByCardNumber(card.getCardNumber()).orElseThrow(CardNotFoundException::new);
        BankAccount bankAccount = card.getBankAccount();
        bankAccount.setValue(bankAccount.getValue().add(amount));
        logger.info("Пополнение карты: " + card.getCardNumber() + " На сумму: " + amount);
    }

    public void withdrawalFromCard(Card card, BigDecimal amount) {
        cardRepository.findCardByCardNumber(card.getCardNumber()).orElseThrow(CardNotFoundException::new);
        BankAccount bankAccount = card.getBankAccount();
        if(bankAccount.getValue().compareTo(amount) < 0) {
            logger.info("Попытка списание с карты: " + card.getCardNumber() + " Недостаточно средств");
            throw new ConfirmationError("Недостаточно средств");
        } else {
            bankAccount.setValue(bankAccount.getValue().subtract(amount));
        }
        logger.info("Списание с карты: " + card.getCardNumber() + " На сумму: " + amount);
    }

    public void validateCard(CardDTO cardDTO) {
        Card cardFromDataBase = findCardByNumber(cardDTO.getCardNumber());
        if(!cardFromDataBase.getCVV().equals(cardDTO.getCVV())) {
            logger.info("Ошибка валидации, введен неправильный код CVV: " + cardDTO.getCardNumber());
            throw new InputDataError("Не правильный код CVV.");
        }
        if(!stringDateFormatter.formatDateToString(cardFromDataBase.getValidTill()).equals(cardDTO.getValidTill())) {
            logger.info("Ошибка валидации, введен неправильный срок действия: " + cardDTO.getCardNumber());
            throw new InputDataError("Не правильный срок действия карты.");
        }
        logger.info("Валидация карты: " + cardDTO.getCardNumber() + " прошла успешно");
    }
}
