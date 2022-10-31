package ru.durov.moneytransferservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ConfirmOperationDTO {

    @NotNull(message = "Поле 'operationID' не должно быть null")
    @NotBlank(message = "Поле 'operationID' не должно быть пустым")
    private String operationId;

    @NotNull(message = "Поле 'code' не должно быть null")
    @NotBlank(message = "Поле 'code' не должно быть пустым")
    private String code;
}
