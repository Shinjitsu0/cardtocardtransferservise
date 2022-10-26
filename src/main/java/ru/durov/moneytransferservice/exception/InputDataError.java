package ru.durov.moneytransferservice.exception;

public class InputDataError extends RuntimeException{
    public InputDataError(String message) {
        super(message);
    }
}
