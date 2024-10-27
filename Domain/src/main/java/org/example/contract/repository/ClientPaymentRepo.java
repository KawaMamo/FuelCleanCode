package org.example.contract.repository;

import org.example.model.ClientPayment;

import java.util.Optional;

public interface ClientPaymentRepo {
    ClientPayment save(ClientPayment payment);
    Optional<ClientPayment> findById(Long id);
    void delete(ClientPayment payment);
}
