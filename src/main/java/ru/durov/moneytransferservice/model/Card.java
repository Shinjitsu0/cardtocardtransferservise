package ru.durov.moneytransferservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "valid_till")
    @Temporal(value = TemporalType.DATE)
    private Date validTill;
    @Column(name = "cvv")
    private String CVV;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;
}
