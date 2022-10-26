package ru.durov.moneytransferservice.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Component
public class OperationDTO {
    @Size(min = 16, max = 16, message = "Номер карты должен состоять из 16 цифр.")
    @NotBlank(message = "Поле не должно быть пустым.")
    private String cardFromNumber;
    @Size(min = 5, max = 5, message = "Номер карты должен состоять из 16 цифр.")
    @NotBlank(message = "Поле не должно быть пустым.")
    private String cardFromValidTill;
    @Size(min = 3, max = 3, message = "CVV карты должен состоять из 3 цифр.")
    @NotBlank(message = "Поле не должно быть пустым.")
    private String cardFromCVV;
    @Size(min = 16, max = 16, message = "Номер карты должен состоять из 16 цифр.")
    @NotBlank(message = "Поле не должно быть пустым.")
    private String cardToNumber;

    private AmountDTO amountDTO;

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public AmountDTO getAmount() {
        return amountDTO;
    }

    public void setAmount(AmountDTO amountDTO) {
        this.amountDTO = amountDTO;
    }


    @Override
    public String toString() {
        return "TransferDTO{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amountDTO +
                '}';
    }
}
