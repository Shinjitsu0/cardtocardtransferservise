package ru.durov.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "currency")
    private String currency;
    @Column(name = "value")
    private BigDecimal value;

    @OneToMany(mappedBy = "bankAccount")
    private List<Card> cards;
}
