package ru.durov.moneytransferservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OperationDTO {
    @Size(min = 16, max = 16, message = "Номер карты отправителя должен состоять из 16 цифр.")
    @NotBlank(message = "Поле 'Номер карты отправителя' не должно быть пустым.")
    @NotNull()
    private String cardFromNumber;

    @Size(min = 5, max = 5, message = "Поле 'Срок действия карты' должно состоять из пяти символов вида ММ/ГГ")
    @NotBlank(message = "Поле 'Срок действия карты' не должно быть пустым.")
    @NotNull()
    private String cardFromValidTill;

    @Size(min = 3, max = 3, message = "CVV карты должен состоять из 3 цифр.")
    @NotBlank(message = "Поле 'CVV' не должно быть пустым.")
    private String cardFromCVV;

    @Size(min = 16, max = 16, message = "Номер карты получателя должен состоять из 16 цифр.")
    @NotBlank(message = "Поле 'Номер карты получателя' не должно быть пустым.")
    @NotNull()
    private String cardToNumber;

    private AmountDTO amount;
}
