package ru.durov.moneytransferservice.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AmountDTO {
    @Size(min = 3, max = 3, message = "Код валюты должен состоять из 3 символов.")
    @NotBlank(message = "Поле 'Валюта' не должно быть пустым.")
    private String currency;

    @Min(value = 1, message = "Минимальная сумма перевода должна быть больше еденицы")
    @NotBlank(message = "Поле 'Сумма' не должно быть пустым.")
    private BigDecimal value;
}
