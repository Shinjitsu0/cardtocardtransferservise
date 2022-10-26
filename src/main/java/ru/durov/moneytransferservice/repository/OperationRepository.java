package ru.durov.moneytransferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.durov.moneytransferservice.model.Operation;

import java.util.Optional;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findOperationByOperationId(UUID id);
}
