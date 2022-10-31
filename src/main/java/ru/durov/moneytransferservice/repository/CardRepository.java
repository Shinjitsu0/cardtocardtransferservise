package ru.durov.moneytransferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.durov.moneytransferservice.model.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findCardByCardNumber(String cardNumber);
}
