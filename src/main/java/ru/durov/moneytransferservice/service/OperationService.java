package ru.durov.moneytransferservice.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.durov.moneytransferservice.dto.AmountDTO;
import ru.durov.moneytransferservice.dto.CardDTO;
import ru.durov.moneytransferservice.dto.ConfirmOperationDTO;
import ru.durov.moneytransferservice.dto.OperationDTO;
import ru.durov.moneytransferservice.exception.ConfirmationError;
import ru.durov.moneytransferservice.exception.InputDataError;
import ru.durov.moneytransferservice.exception.OperationNotFoundException;
import ru.durov.moneytransferservice.model.Card;
import ru.durov.moneytransferservice.model.Operation;
import ru.durov.moneytransferservice.repository.OperationRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final CardService cardService;
    private final VerificationService verificationService;
    private final CommissionService commissionService;


    public OperationService(OperationRepository operationRepository, CardService cardService,
                            VerificationService verificationService, CommissionService commissionService) {
        this.operationRepository = operationRepository;
        this.cardService = cardService;
        this.verificationService = verificationService;
        this.commissionService = commissionService;
    }

    public void saveOperation(Operation operation) {
        operationRepository.save(operation);
    }

    public Optional<Operation> findOperationById(UUID id) {
        return operationRepository.findOperationByOperationId(id);
    }

    public Operation getOperationFromOperationDTO(OperationDTO operationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getFieldErrors().forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(" "));
            throw new InputDataError(sb.toString());
        }
        AmountDTO amountDTO = operationDTO.getAmount();
        CardDTO cardFromDTO = new CardDTO(operationDTO.getCardFromNumber(), operationDTO.getCardFromValidTill(), operationDTO.getCardFromCVV());
        cardService.validateCard(cardFromDTO);
        Card cardFrom = cardService.findCardByNumber(operationDTO.getCardFromNumber());
        Card cardTo = cardService.findCardByNumber(operationDTO.getCardToNumber());
        BigDecimal amount = amountDTO.getValue();
        BigDecimal commission = commissionService.getCommissionFromValue(amount);
        BigDecimal totalAmount = amount.add(commission);

        return Operation.builder()
                .operationId(UUID.randomUUID())
                .cardFrom(cardFrom)
                .cardTo(cardTo)
                .currency(amountDTO.getCurrency())
                .amount(amount)
                .commission(commission)
                .totalAmount(totalAmount)
                .verificationCode(verificationService.getVerificationCode())
                .build();
    }

    public void confirmOperation(ConfirmOperationDTO confirmOperationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getFieldErrors().forEach(fieldError -> sb.append(fieldError.getDefaultMessage()));
            throw new InputDataError(sb.toString());
        }
        UUID operationUUID = UUID.fromString(confirmOperationDTO.getOperationId());
        Operation operation = findOperationById(operationUUID).orElseThrow(OperationNotFoundException::new);
        if(!operation.getVerificationCode().equals(confirmOperationDTO.getCode())){
            throw new ConfirmationError("Введен неправильный подтверждающий код.");
        }
    }

    public String doTransaction(String operationId) {
        UUID operationUUID = UUID.fromString(operationId);
        Operation operation = findOperationById(operationUUID).orElseThrow(OperationNotFoundException::new);
        BigDecimal totalAmount = operation.getTotalAmount();
        BigDecimal amount = operation.getAmount();
        Card cardFrom = operation.getCardFrom();
        Card cardTo = operation.getCardTo();
        cardService.withdrawalFromCard(cardFrom, totalAmount);
        cardService.depositToCard(cardTo, amount);
        operation.setCompleted(true);
        saveOperation(operation);
        return operationUUID.toString();
    }
}
