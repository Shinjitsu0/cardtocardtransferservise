package ru.durov.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
