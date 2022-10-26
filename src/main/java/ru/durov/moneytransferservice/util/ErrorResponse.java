package ru.durov.moneytransferservice.util;

public class ErrorResponse {
    private static ErrorResponse instance;
    private long id;
    private String message;

    public static synchronized ErrorResponse getInstance() {
        if (instance == null) {
            instance = new ErrorResponse();
        }
        return instance;
    }

    private ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        id++;
        this.message = message;
    }

    public long getId() {
        return id;
    }
}
