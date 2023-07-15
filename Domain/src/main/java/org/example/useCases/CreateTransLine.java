package org.example.useCases;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.request.CreateFatTransLineRequest;
import org.example.contract.request.CreateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.mappers.PlaceTypeDetector;
import org.example.mappers.TransLineDomainMapper;
import org.example.model.Place;
import org.example.model.TransLine;
import org.example.validators.CreateTransLineValidator;

import java.time.LocalDateTime;

public class CreateTransLine {
    private final TransLineRepo transLineRepo;
    private final TransLineDomainMapper transLineDomainMapper;
    private final CreateTransLineValidator validator;
    private final PlaceTypeDetector placeTypeDetector;

    public CreateTransLine(TransLineRepo transLineRepo,
                           TransLineDomainMapper transLineDomainMapper,
                           CreateTransLineValidator validator, PlaceTypeDetector placeTypeDetector) {
        this.transLineRepo = transLineRepo;
        this.transLineDomainMapper = transLineDomainMapper;
        this.validator = validator;
        this.placeTypeDetector = placeTypeDetector;
    }

    public TransLineResponse execute(CreateTransLineRequest request){
        validator.validate(request);
        final Place source = placeTypeDetector.detect(request.getSourceId());
        final Place destination = placeTypeDetector.detect(request.getDestinationId());
        final CreateFatTransLineRequest createFatTransLineRequest = getCreateFatTransLineRequest(request, source, destination);
        final TransLine transLine = transLineDomainMapper.requestToDomain(createFatTransLineRequest);
        transLine.setCreatedAt(LocalDateTime.now());
        final TransLine save = transLineRepo.save(transLine);
        return transLineDomainMapper.domainToResponse(save);
    }

    private static CreateFatTransLineRequest getCreateFatTransLineRequest(CreateTransLineRequest request, Place source, Place destination) {
        final CreateFatTransLineRequest createFatTransLineRequest = new CreateFatTransLineRequest();
        createFatTransLineRequest.setSource(source);
        createFatTransLineRequest.setDestination(destination);
        createFatTransLineRequest.setFeeAmount(request.getFeeAmount());
        createFatTransLineRequest.setFeeCurrency(request.getFeeCurrency());
        return createFatTransLineRequest;
    }
}
