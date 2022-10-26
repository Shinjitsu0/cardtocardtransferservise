package ru.durov.moneytransferservice.dto;

public class ConfirmOperationDTO {

    private String operationId;

    private String code;

    public ConfirmOperationDTO(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public ConfirmOperationDTO() {
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperationDTO{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
