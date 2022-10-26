package ru.durov.moneytransferservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VerificationService {
    @Value("${verificationCode.value}")
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }
}
