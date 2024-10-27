package org.example.useCases.delete;

import org.example.contract.repository.SellerPaymentRepo;
import org.example.contract.response.SellerPaymentResponse;
import org.example.mappers.SellerPaymentDomainMapper;
import org.example.model.SellerPayment;

import java.util.NoSuchElementException;

public class DeleteSellerPayment {
    public final SellerPaymentRepo repo;
    public final SellerPaymentDomainMapper mapper;

    public DeleteSellerPayment(SellerPaymentRepo repo, SellerPaymentDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    public SellerPaymentResponse delete(Long id) {
        final SellerPayment sellerPayment = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(sellerPayment);
        return mapper.domainToResponse(sellerPayment);
    }
}
