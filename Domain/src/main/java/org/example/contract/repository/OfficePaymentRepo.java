package org.example.contract.repository;

import org.example.model.OfficePayment;

import java.util.Optional;

public interface OfficePaymentRepo {
    OfficePayment save(OfficePayment payment);
    Optional<OfficePayment> findById(Long id);
    void delete(OfficePayment payment);
}
