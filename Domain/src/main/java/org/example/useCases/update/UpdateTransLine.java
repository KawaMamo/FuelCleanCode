package org.example.useCases.update;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.request.update.UpdateFatTransLineRequest;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.mappers.PlaceTypeDetector;
import org.example.mappers.TransLineDomainMapper;
import org.example.model.Place;
import org.example.model.TransLine;
import org.example.validators.update.UpdateTransLineValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateTransLine {
    private final UpdateTransLineValidator validator;
    private final TransLineDomainMapper mapper;
    private final TransLineRepo transLineRepo;
    private final PlaceTypeDetector placeTypeDetector;

    public UpdateTransLine(UpdateTransLineValidator validator,
                           TransLineDomainMapper mapper,
                           TransLineRepo transLineRepo, PlaceTypeDetector placeTypeDetector) {
        this.validator = validator;
        this.mapper = mapper;
        this.transLineRepo = transLineRepo;
        this.placeTypeDetector = placeTypeDetector;
    }

    public TransLineResponse execute(UpdateTransLineRequest request) {
        final TransLine original = transLineRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Place source = placeTypeDetector.detect(request.getSourceId());
        final Place destination = placeTypeDetector.detect(request.getDestinationId());
        final UpdateFatTransLineRequest updateFatTransLineRequest = getUpdateFatTransLineRequest(request, source, destination);
        final TransLine transLine = mapper.requestToDomain(updateFatTransLineRequest);
        transLine.setCreatedAt(original.getCreatedAt());
        transLine.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        transLineRepo.save(transLine);
        return mapper.domainToResponse(transLineRepo.save(transLine));
    }

    private static UpdateFatTransLineRequest getUpdateFatTransLineRequest(UpdateTransLineRequest request, Place source, Place destination) {
        final UpdateFatTransLineRequest updateFatTransLineRequest = new UpdateFatTransLineRequest();
        updateFatTransLineRequest.setId(request.getId());
        updateFatTransLineRequest.setSource(source);
        updateFatTransLineRequest.setDestination(destination);
        updateFatTransLineRequest.setFeeAmount(request.getFeeAmount());
        updateFatTransLineRequest.setFeeCurrency(request.getFeeCurrency());
        return updateFatTransLineRequest;
    }


}
