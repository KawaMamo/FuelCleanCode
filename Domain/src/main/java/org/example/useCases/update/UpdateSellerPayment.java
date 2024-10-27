package org.example.useCases.update;

import org.example.contract.repository.SellerPaymentRepo;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.mappers.SellerPaymentDomainMapper;
import org.example.model.SellerPayment;
import org.example.validators.update.UpdateSellerPaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateSellerPayment {
    private final UpdateSellerPaymentValidator validator;
    private final SellerPaymentRepo repo;
    private final SellerPaymentDomainMapper mapper;

    public UpdateSellerPayment(UpdateSellerPaymentValidator validator, SellerPaymentRepo repo, SellerPaymentDomainMapper mapper) {
        this.validator = validator;
        this.repo = repo;
        this.mapper = mapper;
    }

    public SellerPaymentResponse update(UpdateSellerPaymentRequest request){
        validator.validate(request);
        final SellerPayment original = repo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        final SellerPayment sellerPayment = mapper.requestToDomain(request);
        sellerPayment.setCreatedAt(original.getCreatedAt());
        sellerPayment.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final SellerPayment save = repo.save(sellerPayment);
        return mapper.domainToResponse(save);
    }
}
