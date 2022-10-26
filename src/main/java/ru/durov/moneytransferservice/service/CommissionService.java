package ru.durov.moneytransferservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CommissionService {
    @Value("${rounding.scale}")
    private int roundingScale;
    @Value("${rounding.mode}")
    RoundingMode roundingMode;
    @Value("${commission.pct}")
    private BigDecimal pct;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public BigDecimal getCommissionFromValue(BigDecimal value) {
        return value.multiply(pct).divide(ONE_HUNDRED, roundingScale, roundingMode);
    }
}
