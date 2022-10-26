package ru.durov.moneytransferservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.durov.moneytransferservice.dto.ConfirmOperationDTO;
import ru.durov.moneytransferservice.dto.OperationDTO;
import ru.durov.moneytransferservice.exception.CardNotFoundException;
import ru.durov.moneytransferservice.exception.ConfirmationError;
import ru.durov.moneytransferservice.exception.InputDataError;
import ru.durov.moneytransferservice.model.Operation;
import ru.durov.moneytransferservice.service.OperationService;
import ru.durov.moneytransferservice.util.ErrorResponse;
import ru.durov.moneytransferservice.util.OperationResponse;

import javax.validation.Valid;


@RestController
public class TransferController {
    private final OperationService operationService;

    public TransferController(OperationService operationService) {
        this.operationService = operationService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public OperationResponse transfer(@RequestBody @Valid OperationDTO operationDTO, BindingResult bindingResult) {
        Operation operation = operationService.getOperationFromOperationDTO(operationDTO);
        operationService.saveOperation(operation);
        return new OperationResponse(operation.getOperationId().toString());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/confirmOperation")
    public OperationResponse confirmOperation(@RequestBody ConfirmOperationDTO confirmOperationDTO) {
        operationService.confirmOperation(confirmOperationDTO);
        operationService.doTransaction(confirmOperationDTO.getOperationId());
        return new OperationResponse(confirmOperationDTO.getOperationId());
    }

    @ExceptionHandler(InputDataError.class)
    ResponseEntity<ErrorResponse> invalidCredentials(InputDataError error) {
        ErrorResponse errorResponse = ErrorResponse.getInstance();
        errorResponse.setMessage(error.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    ResponseEntity<ErrorResponse> invalidCardNumber(CardNotFoundException error) {
        ErrorResponse errorResponse = ErrorResponse.getInstance();
        errorResponse.setMessage("Карта не найдена");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorResponse> invalidInputError(IllegalArgumentException error) {
        ErrorResponse errorResponse = ErrorResponse.getInstance();
        errorResponse.setMessage(error.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConfirmationError.class)
    ResponseEntity<ErrorResponse> invalidVerificationNumber(ConfirmationError error) {
        ErrorResponse errorResponse = ErrorResponse.getInstance();
        errorResponse.setMessage(error.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
