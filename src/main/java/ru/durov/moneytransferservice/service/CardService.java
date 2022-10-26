package ru.durov.moneytransferservice.service;

import org.springframework.stereotype.Service;
import ru.durov.moneytransferservice.dto.CardDTO;
import ru.durov.moneytransferservice.exception.CardNotFoundException;
import ru.durov.moneytransferservice.exception.InputDataError;
import ru.durov.moneytransferservice.model.Card;
import ru.durov.moneytransferservice.repository.CardRepository;
import ru.durov.moneytransferservice.util.StringDateFormatter;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final StringDateFormatter stringDateFormatter;

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

    public boolean validateCard(CardDTO cardDTO) {
        Card cardFromDataBase = findCardByNumber(cardDTO.getCardNumber());
        if(!cardFromDataBase.getCVV().equals(cardDTO.getCVV())) {
            throw new InputDataError("Не правильный код CVV.");
        }
        if(!stringDateFormatter.formatDateToString(cardFromDataBase.getValidTill()).equals(cardDTO.getValidTill())) {
            throw new InputDataError("Не правильный срок действия карты.");
        }
        return true;
    }
}
