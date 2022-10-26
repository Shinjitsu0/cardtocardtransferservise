package ru.durov.moneytransferservice.dto;

public class CardDTO {
    private String cardNumber;
    private String validTill;
    private String CVV;

    public CardDTO(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardDTO(String cardNumber, String validTill, String CVV) {
        this.cardNumber = cardNumber;
        this.validTill = validTill;
        this.CVV = CVV;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
