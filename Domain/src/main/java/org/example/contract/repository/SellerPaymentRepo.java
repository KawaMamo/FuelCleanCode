package org.example.contract.repository;

import org.example.model.SellerPayment;

import java.util.Optional;

public interface SellerPaymentRepo {
    SellerPayment save(SellerPayment payment);
    Optional<SellerPayment> findById(Long id);
    void delete(SellerPayment payment);
}
