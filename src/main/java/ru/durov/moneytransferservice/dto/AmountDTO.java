package ru.durov.moneytransferservice.dto;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class AmountDTO {
    private String currency;
    @Min(value = 1, message = "Сумма перевода должна быть не менее 1")
    private BigDecimal value;

    public AmountDTO() {
    }

    public AmountDTO(BigDecimal value,  String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
