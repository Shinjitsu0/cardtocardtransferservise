package ru.durov.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "commission")
    private BigDecimal commission;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "is_completed")
    private boolean isCompleted;
}

