package ru.durov.moneytransferservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @Column(name = "operation_id")
    private UUID operationId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_from_id")
    private Card cardFrom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_to_id")
    private Card cardTo;
    @Column(name = "currency")
    private String currency;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "commission")
    private BigDecimal commission;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "is_completed")
    private boolean isCompleted;
}

