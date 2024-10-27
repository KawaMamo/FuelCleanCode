package org.example.useCases.create;

import org.example.contract.repository.SellerPaymentRepo;
import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.mappers.SellerPaymentDomainMapper;
import org.example.model.SellerPayment;
import org.example.validators.create.CreateSellerPaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateSellerPayment {
    private final CreateSellerPaymentValidator validator;
    private final SellerPaymentRepo repo;
    public final SellerPaymentDomainMapper mapper;

    public CreateSellerPayment(CreateSellerPaymentValidator validator, SellerPaymentRepo repo, SellerPaymentDomainMapper mapper) {
        this.validator = validator;
        this.repo = repo;
        this.mapper = mapper;
    }

    public SellerPaymentResponse create(CreateSellerPaymentRequest request){
        validator.validate(request);
        final SellerPayment sellerPayment = mapper.requestToDomain(request);
        sellerPayment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final SellerPayment save = repo.save(sellerPayment);
        return mapper.domainToResponse(save);
    }
}
