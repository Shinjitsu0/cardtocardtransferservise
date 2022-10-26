package ru.durov.moneytransferservice.exception;

public class ConfirmationError extends RuntimeException{
    public ConfirmationError(String message) {
        super(message);
    }
}
